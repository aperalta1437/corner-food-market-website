package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.configuration.administrator.JwtTokenProvider;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.repository.AdministratorRepository;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin/login")
@CrossOrigin(origins = "http://localhost:3000")
public class AdministratorLoginController {

    private static Logger log = LoggerFactory.getLogger(AdministratorLoginController.class);

    @Qualifier(value = "administratorAuthenticationManagerBean")
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AdministratorRepository administratorRepository;

    @GetMapping
    public String getTest() {
        return "Hello World";
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody Administrator administrator) throws Exception {
        log.info("AdministratorLoginController : authenticate");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                administrator.getEmail(), administrator.getPassword()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", jwtTokenProvider.createToken(administrator.getEmail()));

        return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
    }
}
