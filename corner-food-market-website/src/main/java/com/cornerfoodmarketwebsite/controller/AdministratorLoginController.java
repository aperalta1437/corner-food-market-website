package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorFirstFactorLoginFields;
import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorSecondFactorLoginFields;
import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.business.dto.response.AuthenticationResponse;
import com.cornerfoodmarketwebsite.business.dto.response.FinalAuthenticationResponse;
import com.cornerfoodmarketwebsite.business.dto.response.FirstFactorAuthenticationResponse;
import com.cornerfoodmarketwebsite.business.dto.response.LoginDependenciesResponse;
import com.cornerfoodmarketwebsite.business.service.AdministratorLoginService;
import com.cornerfoodmarketwebsite.business.service.utils.*;
import com.cornerfoodmarketwebsite.configuration.administrator.JwtTokenProvider;
import com.cornerfoodmarketwebsite.configuration.administrator.TfaJwtTokenProvider;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.repository.AdministratorRepository;
import com.cornerfoodmarketwebsite.exception.InvalidTfaCodeRuntimeException;
import com.cornerfoodmarketwebsite.exception.administrator.FailedAccountAuthenticationRuntimeException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Slf4j
@RestController
@RequestMapping(value = "/admin/login")
public class AdministratorLoginController {

    private final AuthenticationManager administratorPreTfaAuthenticationManager;
    private final AuthenticationManager administratorPostTfaAuthenticationManager;
    private final TfaJwtTokenProvider tfaJwtTokenProvider;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdministratorLoginService administratorLoginService;
    private final LoginRsaKeysStore loginRsaKeysStore;
    private final TemporalEncryptedPasswordStore temporalEncryptedPasswordStore;

    @Autowired
    public AdministratorLoginController(@Qualifier(value = "administratorPreTfaAuthenticationManagerBean") AuthenticationManager administratorPreTfaAuthenticationManager,
                                        @Qualifier(value = "administratorPostTfaAuthenticationManagerBean") AuthenticationManager administratorPostTfaAuthenticationManager,
                                        TfaJwtTokenProvider tfaJwtTokenProvider, JwtTokenProvider jwtTokenProvider, AdministratorLoginService administratorLoginService,
                                        LoginRsaKeysStore loginRsaKeysStore, TemporalEncryptedPasswordStore temporalEncryptedPasswordStore) {
        this.administratorPreTfaAuthenticationManager = administratorPreTfaAuthenticationManager;
        this.administratorPostTfaAuthenticationManager = administratorPostTfaAuthenticationManager;
        this.tfaJwtTokenProvider = tfaJwtTokenProvider;
        this.jwtTokenProvider = jwtTokenProvider;
        this.administratorLoginService = administratorLoginService;
        this.loginRsaKeysStore = loginRsaKeysStore;
        this.temporalEncryptedPasswordStore = temporalEncryptedPasswordStore;
    }

    @GetMapping(value = "/dependencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginDependenciesResponse getLoginDependencies(@RequestHeader("Origin-Number") int originNumber) {
        Base64PublicKeyInformation base64PublicKeyInformation = this.loginRsaKeysStore.getBase64PublicKeyInformation(originNumber);
        return new LoginDependenciesResponse(base64PublicKeyInformation.getBase64PublicKey(), base64PublicKeyInformation.getKeysMapAccessNumber());
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationResponse authenticate(@RequestBody AdministratorFirstFactorLoginFields administratorFirstFactorLoginFields, @RequestHeader("Origin-Number") int originNumber) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException, MessagingException, NotProvidedTfaTypeException, NotSupportedTfaTypeException {
        String password = RsaUtil.decrypt(administratorFirstFactorLoginFields.getEncryptedPassword(), this.loginRsaKeysStore.getPrivateKey(administratorFirstFactorLoginFields.getLoginAccessCode(), originNumber));
        FirstFactorAuthenticationInformation firstFactorAuthenticationInformation = this.administratorLoginService.verifyCredentialsAndGetFirstFactorAuthenticationInformation(administratorFirstFactorLoginFields.getEmail(), password);

        if (firstFactorAuthenticationInformation.isTfaEnabled()) {
            this.temporalEncryptedPasswordStore.putEncryptedPassword(originNumber, firstFactorAuthenticationInformation.getUserId(), administratorFirstFactorLoginFields.getEncryptedPassword());
            return tfaPreAuthenticate(administratorFirstFactorLoginFields.getEmail(), password);
        } else {
            Authentication authentication = administratorPostTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    administratorFirstFactorLoginFields.getEmail(), password));
            if (authentication.isAuthenticated()) {
                Administrator administrator = ((AdministratorUserDetails) authentication.getPrincipal()).getAdministrator();
                return new FinalAuthenticationResponse(administrator.getPermissions(), jwtTokenProvider.createToken(administratorFirstFactorLoginFields.getEmail()), administrator.getId(), String.format("%s %s", administrator.getFirstName(), administrator.getLastName()), administrator.getEmail());
            } else {
                throw new FailedAccountAuthenticationRuntimeException();
            }
        }
    }

    public FirstFactorAuthenticationResponse tfaPreAuthenticate(String email, String password) throws MessagingException, NotProvidedTfaTypeException, NotSupportedTfaTypeException {
        Authentication authentication = administratorPreTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, password));

        if (authentication.isAuthenticated()) {
            Administrator administrator = ((AdministratorUserDetails) authentication.getPrincipal()).getAdministrator();

            TfaCodeDetailsForUser tfaCodeDetailsForUser = this.administratorLoginService.sendTfaCodeAndGetDetailsForUser(administrator);
            return new FirstFactorAuthenticationResponse(tfaCodeDetailsForUser.getValidTimeframe(), tfaCodeDetailsForUser.getCreatedAt(), tfaJwtTokenProvider.createToken(administrator.getEmail()), administrator.getId(), String.format("%s %s", administrator.getFirstName(), administrator.getLastName()), administrator.getEmail());
        } else {
            throw new FailedAccountAuthenticationRuntimeException();
        }
    }

    @PostMapping(value = "/tfa-post-authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public FinalAuthenticationResponse tfaPostAuthenticate(@RequestHeader("Authorization") String tfaJwtToken, @RequestBody AdministratorSecondFactorLoginFields administratorSecondFactorLoginFields, @RequestHeader("Origin-Number") int originNumber) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Claims claims = tfaJwtTokenProvider.getClaimsFromToken(tfaJwtToken);

        String administratorEmail = claims.getSubject();

        String tfaCode = RsaUtil.decrypt(administratorSecondFactorLoginFields.getEncryptedTfaCode(), this.loginRsaKeysStore.getPrivateKey(administratorSecondFactorLoginFields.getLoginAccessCode(), originNumber));

        if (this.administratorLoginService.isCorrectTfaCodeByAdministrator(tfaCode, administratorSecondFactorLoginFields.getId())) {
            String password = RsaUtil.decrypt(this.temporalEncryptedPasswordStore.getEncryptedPassword(originNumber, administratorSecondFactorLoginFields.getId()), this.loginRsaKeysStore.getPrivateKey(administratorSecondFactorLoginFields.getLoginAccessCode(), originNumber));
//                SecurityContextHolder.clearContext();
            Authentication authentication = administratorPostTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    administratorEmail, password));
            if (authentication.isAuthenticated()) {
                Administrator administrator = ((AdministratorUserDetails) authentication.getPrincipal()).getAdministrator();
                return new FinalAuthenticationResponse(administrator.getPermissions(), jwtTokenProvider.createToken(administratorEmail), administrator.getId(), String.format("%s %s", administrator.getFirstName(), administrator.getLastName()), administrator.getEmail());
            } else {
                throw new FailedAccountAuthenticationRuntimeException();
            }
        } else {
            throw new InvalidTfaCodeRuntimeException();
        }
    }
}
