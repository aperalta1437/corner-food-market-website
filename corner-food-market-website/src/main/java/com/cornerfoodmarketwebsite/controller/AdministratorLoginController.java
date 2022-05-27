package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorFirstFactorLoginFields;
import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorSecondFactorLoginFields;
import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.business.service.AdministratorLoginService;
import com.cornerfoodmarketwebsite.business.service.ExceptionLogService;
import com.cornerfoodmarketwebsite.business.service.utils.*;
import com.cornerfoodmarketwebsite.configuration.administrator.JwtTokenProvider;
import com.cornerfoodmarketwebsite.configuration.administrator.TfaJwtTokenProvider;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.repository.AdministratorRepository;
import io.jsonwebtoken.Claims;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin/login")
public class AdministratorLoginController {

    private static Logger log = LoggerFactory.getLogger(AdministratorLoginController.class);
    private final AuthenticationManager administratorPreTfaAuthenticationManager;
    private final AuthenticationManager administratorPostTfaAuthenticationManager;
    private final TfaJwtTokenProvider tfaJwtTokenProvider;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdministratorLoginService administratorLoginService;
    private final ExceptionLogService exceptionLogService;
    private final LoginRsaKeysStore loginRsaKeysStore;
    private final TemporalEncryptedPasswordStore temporalEncryptedPasswordStore;

    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorLoginController(@Qualifier(value = "administratorPreTfaAuthenticationManagerBean") AuthenticationManager administratorPreTfaAuthenticationManager,
                                        @Qualifier(value = "administratorPostTfaAuthenticationManagerBean") AuthenticationManager administratorPostTfaAuthenticationManager,
                                        TfaJwtTokenProvider tfaJwtTokenProvider, JwtTokenProvider jwtTokenProvider, AdministratorLoginService administratorLoginService,
                                        ExceptionLogService exceptionLogService, LoginRsaKeysStore loginRsaKeysStore, TemporalEncryptedPasswordStore temporalEncryptedPasswordStore, AdministratorRepository administratorRepository) {
        this.administratorPreTfaAuthenticationManager = administratorPreTfaAuthenticationManager;
        this.administratorPostTfaAuthenticationManager = administratorPostTfaAuthenticationManager;
        this.tfaJwtTokenProvider = tfaJwtTokenProvider;
        this.jwtTokenProvider = jwtTokenProvider;
        this.administratorLoginService = administratorLoginService;
        this.exceptionLogService = exceptionLogService;
        this.loginRsaKeysStore = loginRsaKeysStore;
        this.temporalEncryptedPasswordStore = temporalEncryptedPasswordStore;
        this.administratorRepository = administratorRepository;
    }

    @GetMapping(value = "/dependencies")
    public ResponseEntity<String> getLoginDependencies(@RequestHeader("Origin-Number") int originNumber) {
        JSONObject jsonResponse = new JSONObject();

        try {
            Base64PublicKeyInformation base64PublicKeyInformation = this.loginRsaKeysStore.getBase64PublicKeyInformation(originNumber);
            jsonResponse.put("loginBase64RsaPublicKey", base64PublicKeyInformation.getBase64PublicKey());
            jsonResponse.put("loginAccessCode", base64PublicKeyInformation.getKeysMapAccessNumber());     // Different name in JSON payload to conceal purpose from the user
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
        } catch (Exception exception) {
            try {
                jsonResponse.put("message", "An issue happened at the server. Please try again later. If the issue persist, please contact your system administrator.");
            } catch (JSONException jsonException) {
                this.exceptionLogService.logException(jsonException);
                jsonException.printStackTrace();
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            this.exceptionLogService.logException(exception);
            exception.printStackTrace();
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody AdministratorFirstFactorLoginFields administratorFirstFactorLoginFields, @RequestHeader("Origin-Number") int originNumber) {
        JSONObject jsonResponse = new JSONObject();

        try {
            String password = RsaUtil.decrypt(administratorFirstFactorLoginFields.getEncryptedPassword(), this.loginRsaKeysStore.getPrivateKey(administratorFirstFactorLoginFields.getLoginAccessCode(), originNumber));
            Optional<FirstFactorAuthenticationInformation> optionalFirstFactorAuthenticationInformation = this.administratorLoginService.verifyCredentialsAndGetFirstFactorAuthenticationInformation(administratorFirstFactorLoginFields.getEmail(), password);
            if (optionalFirstFactorAuthenticationInformation.isPresent()) {
                FirstFactorAuthenticationInformation firstFactorAuthenticationInformation = optionalFirstFactorAuthenticationInformation.get();
                if (firstFactorAuthenticationInformation.isTfaEnabled()) {
                    this.temporalEncryptedPasswordStore.putEncryptedPassword(originNumber, firstFactorAuthenticationInformation.getUserId(), administratorFirstFactorLoginFields.getEncryptedPassword());
                    return tfaPreAuthenticate(administratorFirstFactorLoginFields.getEmail(), password, administratorFirstFactorLoginFields.getLoginAccessCode());
                } else {
                    Authentication authentication = administratorPostTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            administratorFirstFactorLoginFields.getEmail(), password));

                    if (authentication.isAuthenticated()) {
                        jsonResponse.put("isTfaEnabled", false);
                        jsonResponse.put("accessToken", jwtTokenProvider.createToken(administratorFirstFactorLoginFields.getEmail()));    // TODO implement roles
                        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
                    } else {
                        jsonResponse.put("message", "We were unable to authenticate your account. Please try again later. If the issue persist, please contact your system administrator.");
                        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.SERVICE_UNAVAILABLE);
                    }
                }
            } else {
                throw new BadCredentialsException("Incorrect credentials were given");
            }
        } catch (BadCredentialsException badCredentialsException) {
            try {
                jsonResponse.put("message", "Invalid email and/or password.");
            } catch (JSONException jsonException) {
                this.exceptionLogService.logException(jsonException);
                jsonException.printStackTrace();
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.UNAUTHORIZED);
            }
            this.exceptionLogService.logException(badCredentialsException);
            badCredentialsException.printStackTrace();
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.UNAUTHORIZED);
        } catch (Exception exception) {
            try {
                jsonResponse.put("message", "An issue happened at the server. Please try again later. If the issue persist, please contact your system administrator");
            } catch (JSONException jsonException) {
                this.exceptionLogService.logException(jsonException);
                jsonException.printStackTrace();
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            this.exceptionLogService.logException(exception);
            exception.printStackTrace();
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> tfaPreAuthenticate(String email, String password, long loginAccessCode) throws JSONException, NotProvidedTfaTypeException, NotSupportedTfaTypeException, MessagingException {
        JSONObject jsonResponse = new JSONObject();

        Authentication authentication = administratorPreTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, password));

        if (authentication.isAuthenticated()) {
            Administrator administrator = ((AdministratorUserDetails) authentication.getPrincipal()).getAdministrator();

            jsonResponse.put("isTfaEnabled", true);
            jsonResponse.put("accessToken", tfaJwtTokenProvider.createToken(administrator.getEmail()));    // TODO implement roles
            jsonResponse.put("tfaExpirationTimeInMilliseconds", this.administratorLoginService.sendTfaCodeAndGetExpirationTime(administrator));
            jsonResponse.put("loginAccessCode", loginAccessCode);
        } else {
            jsonResponse.put("message", "We were unable to authenticate your account. Please try again later. If the issue persist, please contact your system administrator.");
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/tfa-post-authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> tfaPostAuthenticate(@RequestHeader("Authorization") String tfaJwtToken, @RequestBody AdministratorSecondFactorLoginFields administratorSecondFactorLoginFields, @RequestHeader("Origin-Number") int originNumber) {
        System.out.println("Inside tfaPostAuthenticate HEREEEEEEEE!");

        JSONObject jsonResponse = new JSONObject();

        Claims claims = tfaJwtTokenProvider.getClaimsFromToken(tfaJwtToken);

        String administratorEmail = claims.getSubject();

        try {
            if (this.administratorLoginService.isCorrectTfaCodeByAdministrator(administratorSecondFactorLoginFields.getTfaCode(), administratorSecondFactorLoginFields.getId())) {
                String password = RsaUtil.decrypt(this.temporalEncryptedPasswordStore.getEncryptedPassword(originNumber, administratorSecondFactorLoginFields.getId()), this.loginRsaKeysStore.getPrivateKey(administratorSecondFactorLoginFields.getLoginAccessCode(), originNumber));
//                SecurityContextHolder.clearContext();
                Authentication authentication = administratorPostTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        administratorEmail, password));
                System.out.println("Inside tfaPostAuthenticate HEREEEEEEEE 222222222222222222222222!");
                System.out.println(password);
                if (authentication.isAuthenticated()) {
                    System.out.println("Inside tfaPostAuthenticate HEREEEEEEEE 333333333333333333333333!");

                    jsonResponse.put("accessToken", jwtTokenProvider.createToken(administratorEmail));    // TODO implement roles

                    System.out.println("Inside tfaPostAuthenticate HEREEEEEEEE 444444444444444444444444!");
                } else {
                    jsonResponse.put("message", "We were unable to authenticate your account. Please try again later. If the issue persist, please contact your system administrator.");
                    return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.SERVICE_UNAVAILABLE);
                }
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
            } else {
                jsonResponse.put("message", "Incorrect security code was given.");
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (BadCredentialsException badCredentialsException) {
            try {
                jsonResponse.put("message", "Invalid email and/or password.");
            } catch (JSONException jsonException) {
                this.exceptionLogService.logException(jsonException);
                jsonException.printStackTrace();
            }
            this.exceptionLogService.logException(badCredentialsException);
            badCredentialsException.printStackTrace();
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.UNAUTHORIZED);
        } catch (Exception exception) {
            try {
                jsonResponse.put("message", "An issue happened at the server. Please try again later. If the issue persist, please contact your system administrator");
            } catch (JSONException jsonException) {
                this.exceptionLogService.logException(jsonException);
                jsonException.printStackTrace();
            }
            this.exceptionLogService.logException(exception);
            exception.printStackTrace();
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
