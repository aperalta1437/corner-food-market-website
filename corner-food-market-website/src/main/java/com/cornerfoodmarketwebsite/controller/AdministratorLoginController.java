package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.business.service.AdministratorLoginService;
import com.cornerfoodmarketwebsite.configuration.administrator.JwtTokenProvider;
import com.cornerfoodmarketwebsite.configuration.administrator.TfaJwtTokenProvider;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin/login")
@CrossOrigin(origins = "http://localhost:3000")
public class AdministratorLoginController {

    private static Logger log = LoggerFactory.getLogger(AdministratorLoginController.class);

    @Qualifier(value = "administratorPreTfaAuthenticationManagerBean")
    @Autowired
    private AuthenticationManager administratorPreTfaAuthenticationManager;

    @Qualifier(value = "administratorPostTfaAuthenticationManagerBean")
    @Autowired
    private AuthenticationManager administratorPostTfaAuthenticationManager;

    @Autowired
    private TfaJwtTokenProvider tfaJwtTokenProvider;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final AdministratorLoginService administratorLoginService;

    @Autowired
    public AdministratorLoginController(AdministratorLoginService administratorLoginService) {
        this.administratorLoginService = administratorLoginService;
    }

//    @Autowired
//    private AdministratorRepository administratorRepository;

    @PostMapping(value = "/tfa-pre-authenticate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> tfaPreAuthenticate(@RequestBody Administrator administrator) throws Exception {
        log.info("AdministratorLoginController : authenticate");
        JSONObject jsonResponse = new JSONObject();

        try {
            Authentication authentication = administratorPreTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    administrator.getEmail(), administrator.getPassword()));

            if (authentication.isAuthenticated()) {
                jsonResponse.put("Email", authentication.getName());
                jsonResponse.put("Authorities", authentication.getAuthorities());
                jsonResponse.put("Token", tfaJwtTokenProvider.createToken(administrator.getEmail()));    // TODO implement roles
                jsonResponse.put("Tfa-Expiration-Time-In-Milliseconds",
                        this.administratorLoginService.sendTfaCodeAndGetExpirationTime(
                                ((AdministratorUserDetails) authentication.getPrincipal()).getAdministrator()));
            }
        } catch (Exception exception) {
            jsonResponse.put("exception", "An issue happened at the server. Please try again later. If the issue persist, please contact your system administrator");

            exception.printStackTrace();

            return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/tfa-post-authenticate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> tfaPostAuthenticate(@RequestBody Administrator administrator) throws Exception {
        log.info("AdministratorLoginController : authenticateTfa");
        JSONObject jsonObject = new JSONObject();

        try {
            Authentication authentication = administratorPreTfaAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    administrator.getEmail(), administrator.getPassword()));

            if (authentication.isAuthenticated()) {
                jsonObject.put("email", authentication.getName());
                jsonObject.put("authorities", authentication.getAuthorities());
                jsonObject.put("token", jwtTokenProvider.createToken(administrator.getEmail()));    // TODO implement roles
            }
        } catch (JSONException jsonException) {
            try {
                jsonObject.put("exception", jsonException.getMessage());
            } catch (JSONException jsonException1) {
                jsonException1.printStackTrace();
            }
            return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
    }
}
