package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorFirstFactorLoginFields;
import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorSecondFactorLoginFields;
import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.business.dto.response.*;
import com.cornerfoodmarketwebsite.business.service.AdministratorLoginService;
import com.cornerfoodmarketwebsite.business.service.utils.*;
import com.cornerfoodmarketwebsite.configuration.administrator.AccessTokenProvider;
import com.cornerfoodmarketwebsite.configuration.administrator.RefreshTokenProvider;
import com.cornerfoodmarketwebsite.configuration.administrator.TfaAccessTokenProvider;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.exception.InvalidTfaCodeRuntimeException;
import com.cornerfoodmarketwebsite.exception.administrator.FailedAccountAuthenticationRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.cornerfoodmarketwebsite.helper.Constants.ORIGIN_NUMBER_HEADER_NAME;

@Slf4j
@RestController
@RequestMapping(value = "/admin/login")
@RequiredArgsConstructor
public class AdministratorLoginController {

    @Qualifier(value = "administratorPreTfaAuthenticationManagerBean")
    private final AuthenticationManager administratorPreTfaAuthenticationManager;
    @Qualifier(value = "administratorPostTfaAuthenticationManagerBean")
    private final AuthenticationManager administratorPostTfaAuthenticationManager;
    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenProvider refreshTokenProvider;
    private final AdministratorLoginService administratorLoginService;
    private final LoginRsaKeysStore loginRsaKeysStore;

    @GetMapping(value = "/dependencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginDependenciesResponse getLoginDependencies(@RequestHeader(ORIGIN_NUMBER_HEADER_NAME) int originNumber) {
        Base64PublicKeyInformation base64PublicKeyInformation = this.loginRsaKeysStore.getBase64PublicKeyInformation(originNumber);
        return new LoginDependenciesResponse(base64PublicKeyInformation.getBase64PublicKey(), base64PublicKeyInformation.getKeysMapAccessNumber());
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationResponse authenticate(@RequestBody AdministratorFirstFactorLoginFields administratorFirstFactorLoginFields, @RequestHeader(ORIGIN_NUMBER_HEADER_NAME) int originNumber) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException, MessagingException, NotProvidedTfaTypeException, NotSupportedTfaTypeException {
        String password = RsaUtil.decrypt(administratorFirstFactorLoginFields.getEncryptedPassword(), this.loginRsaKeysStore.getPrivateKey(administratorFirstFactorLoginFields.getLoginAccessCode(), originNumber));
        FirstFactorAuthenticationInformation firstFactorAuthenticationInformation = this.administratorLoginService.verifyCredentialsAndGetFirstFactorAuthenticationInformation(administratorFirstFactorLoginFields.getEmail(), password);

        if (firstFactorAuthenticationInformation.isTfaEnabled()) {
            return tfaPreAuthenticate(administratorFirstFactorLoginFields.getEmail(), password, originNumber);
        } else {
            Authentication authentication = administratorPostTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    administratorFirstFactorLoginFields.getEmail(), password));
            if (authentication.isAuthenticated()) {
                Administrator administrator = ((AdministratorUserDetails) authentication.getPrincipal()).getAdministrator();
                return new FinalAuthenticationResponse(administrator.getPermissions(), accessTokenProvider.createToken(administratorFirstFactorLoginFields.getEmail(), originNumber), administrator.getId(), String.format("%s %s", administrator.getFirstName(), administrator.getLastName()), administrator.getEmail(), refreshTokenProvider.createToken(administrator.getEmail(), originNumber));
            } else {
                throw new FailedAccountAuthenticationRuntimeException();
            }
        }
    }

    public AuthenticationResponse tfaPreAuthenticate(String email, String password, int originNumber) throws MessagingException, NotProvidedTfaTypeException, NotSupportedTfaTypeException {
        Authentication authentication = administratorPreTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, password));

        if (authentication.isAuthenticated()) {
            Administrator administrator = ((AdministratorUserDetails) authentication.getPrincipal()).getAdministrator();

            TokenDetails tfaAccessTokenDetails = this.administratorLoginService.sendTfaCodeAndGetDetailsForUser(administrator, originNumber);
            return new AuthenticationResponse(tfaAccessTokenDetails, administrator.getId(), String.format("%s %s", administrator.getFirstName(), administrator.getLastName()), administrator.getEmail());
        } else {
            throw new FailedAccountAuthenticationRuntimeException();
        }
    }

    @PostMapping(value = "/tfa-post-authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public FinalAuthenticationResponse tfaPostAuthenticate(@RequestBody AdministratorSecondFactorLoginFields administratorSecondFactorLoginFields, @RequestHeader(ORIGIN_NUMBER_HEADER_NAME) int originNumber) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Administrator administrator = ((AdministratorUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAdministrator();
        String tfaCode = RsaUtil.decrypt(administratorSecondFactorLoginFields.getEncryptedTfaCode(), this.loginRsaKeysStore.getPrivateKey(administratorSecondFactorLoginFields.getLoginAccessCode(), originNumber));

        if (this.administratorLoginService.isCorrectTfaCodeByAdministrator(tfaCode, administrator.getId())) {
            return new FinalAuthenticationResponse(administrator.getPermissions(), accessTokenProvider.createToken(administrator.getEmail(), originNumber), administrator.getId(), String.format("%s %s", administrator.getFirstName(), administrator.getLastName()), administrator.getEmail(), refreshTokenProvider.createToken(administrator.getEmail(), originNumber));
        } else {
            throw new InvalidTfaCodeRuntimeException();
        }
    }

    @PostMapping(value = "/re-authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReAuthenticationResponse reAuthenticate(@RequestHeader(ORIGIN_NUMBER_HEADER_NAME) int originNumber) {
        Administrator administrator = ((AdministratorUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAdministrator();

        return new ReAuthenticationResponse(accessTokenProvider.createToken(administrator.getEmail(), originNumber), refreshTokenProvider.createToken(administrator.getEmail(), originNumber));
    }
}
