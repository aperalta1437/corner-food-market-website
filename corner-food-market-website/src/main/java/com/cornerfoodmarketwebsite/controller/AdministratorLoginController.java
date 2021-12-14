package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorFirstFactorLoginFields;
import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorSecondFactorLoginFields;
import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.business.service.AdministratorLoginService;
import com.cornerfoodmarketwebsite.business.service.ExceptionLogService;
import com.cornerfoodmarketwebsite.business.service.utils.NotProvidedTfaTypeException;
import com.cornerfoodmarketwebsite.business.service.utils.NotSupportedTfaTypeException;
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

@RestController
@RequestMapping(value = "/api/admin/login")
public class AdministratorLoginController {

    private static Logger log = LoggerFactory.getLogger(AdministratorLoginController.class);
    private final AuthenticationManager administratorPreTfaAuthenticationManager;
    private final AuthenticationManager administratorPostTfaAuthenticationManager;
    private final TfaJwtTokenProvider tfaJwtTokenProvider;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdministratorLoginService administratorLoginService;
    private final ExceptionLogService exceptionLogService;

    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorLoginController(@Qualifier(value = "administratorPreTfaAuthenticationManagerBean") AuthenticationManager administratorPreTfaAuthenticationManager,
                                        @Qualifier(value = "administratorPostTfaAuthenticationManagerBean") AuthenticationManager administratorPostTfaAuthenticationManager,
                                        TfaJwtTokenProvider tfaJwtTokenProvider, JwtTokenProvider jwtTokenProvider, AdministratorLoginService administratorLoginService,
                                        ExceptionLogService exceptionLogService, AdministratorRepository administratorRepository) {
        this.administratorPreTfaAuthenticationManager = administratorPreTfaAuthenticationManager;
        this.administratorPostTfaAuthenticationManager = administratorPostTfaAuthenticationManager;
        this.tfaJwtTokenProvider = tfaJwtTokenProvider;
        this.jwtTokenProvider = jwtTokenProvider;
        this.administratorLoginService = administratorLoginService;
        this.exceptionLogService = exceptionLogService;
        this.administratorRepository = administratorRepository;
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody AdministratorFirstFactorLoginFields administratorFirstFactorLoginFields) {
        JSONObject jsonResponse = new JSONObject();

        try {
            if (this.administratorLoginService.isCorrectCredentials(administratorFirstFactorLoginFields.getEmail(), administratorFirstFactorLoginFields.getPassword())) {
                if (this.administratorRepository.getIsTfaEnabledByEmail(administratorFirstFactorLoginFields.getEmail())) {
                    return tfaPreAuthenticate(administratorFirstFactorLoginFields);
                } else {
                    Authentication authentication = administratorPostTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            administratorFirstFactorLoginFields.getEmail(), administratorFirstFactorLoginFields.getPassword()));

                    if (authentication.isAuthenticated()) {
                        jsonResponse.put("Is-Tfa-Enabled", false);
                        jsonResponse.put("Access-Token", jwtTokenProvider.createToken(administratorFirstFactorLoginFields.getEmail()));    // TODO implement roles
                        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
                    } else {
                        jsonResponse.put("Message", "We were unable to authenticate your account. Please try again later. If the issue persist, please contact your system administrator.");
                        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.SERVICE_UNAVAILABLE);
                    }
                }
            } else {
                throw new BadCredentialsException("Incorrect credentials were given");
            }
        } catch (BadCredentialsException badCredentialsException) {
            try {
                jsonResponse.put("Message", "Invalid email and/or password.");
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
                jsonResponse.put("Message", "An issue happened at the server. Please try again later. If the issue persist, please contact your system administrator");
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

    public ResponseEntity<String> tfaPreAuthenticate(AdministratorFirstFactorLoginFields administratorFirstFactorLoginFields) throws JSONException, NotProvidedTfaTypeException, NotSupportedTfaTypeException {
        JSONObject jsonResponse = new JSONObject();

        Authentication authentication = administratorPreTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                administratorFirstFactorLoginFields.getEmail(), administratorFirstFactorLoginFields.getPassword()));

        if (authentication.isAuthenticated()) {
            Administrator administrator = ((AdministratorUserDetails) authentication.getPrincipal()).getAdministrator();

            jsonResponse.put("Is-Tfa-Enabled", true);
            jsonResponse.put("Access-Token", tfaJwtTokenProvider.createToken(administrator.getEmail()));    // TODO implement roles
            jsonResponse.put("Tfa-Expiration-Time-In-Milliseconds", this.administratorLoginService.sendTfaCodeAndGetExpirationTime(administrator));
            jsonResponse.put("Base64-Rsa-Public-Key", this.administratorLoginService.getBase64RsaPublicKeyByAdministratorId(administrator.getId()));
        } else {
            jsonResponse.put("Message", "We were unable to authenticate your account. Please try again later. If the issue persist, please contact your system administrator.");
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/tfa-post-authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> tfaPostAuthenticate(@RequestHeader("Authorization") String tfaJwtToken, @RequestBody AdministratorSecondFactorLoginFields administratorSecondFactorLoginFields) {
        System.out.println("Inside tfaPostAuthenticate HEREEEEEEEE!");

        JSONObject jsonResponse = new JSONObject();

        Claims claims = tfaJwtTokenProvider.getClaimsFromToken(tfaJwtToken);

        String administratorEmail = claims.getSubject();

        try {
            short administratorId = this.administratorRepository.getIdByEmail(administratorEmail);

            if (this.administratorLoginService.isCorrectTfaCodeByAdministrator(administratorSecondFactorLoginFields.getTfaCode(), administratorId)) {
//                SecurityContextHolder.clearContext();
                Authentication authentication = administratorPostTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        administratorEmail, this.administratorLoginService.decryptTextByAdministrator(administratorSecondFactorLoginFields.getPassword(), administratorId)));
                System.out.println("Inside tfaPostAuthenticate HEREEEEEEEE 222222222222222222222222!");
                System.out.println(this.administratorLoginService.decryptTextByAdministrator(administratorSecondFactorLoginFields.getPassword(), administratorId));
                if (authentication.isAuthenticated()) {
                    System.out.println("Inside tfaPostAuthenticate HEREEEEEEEE 333333333333333333333333!");

                    jsonResponse.put("Access-Token", jwtTokenProvider.createToken(administratorEmail));    // TODO implement roles

                    System.out.println("Inside tfaPostAuthenticate HEREEEEEEEE 444444444444444444444444!");
                } else {
                    jsonResponse.put("Message", "We were unable to authenticate your account. Please try again later. If the issue persist, please contact your system administrator.");
                    return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.SERVICE_UNAVAILABLE);
                }
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
            } else {
                jsonResponse.put("Message", "Incorrect security code was given.");
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (BadCredentialsException badCredentialsException) {
            try {
                jsonResponse.put("Message", "Invalid email and/or password.");
            } catch (JSONException jsonException) {
                this.exceptionLogService.logException(jsonException);
                jsonException.printStackTrace();
            }
            this.exceptionLogService.logException(badCredentialsException);
            badCredentialsException.printStackTrace();
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.UNAUTHORIZED);
        } catch (Exception exception) {
            try {
                jsonResponse.put("Message", "An issue happened at the server. Please try again later. If the issue persist, please contact your system administrator");
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
