package com.cornerfoodmarketwebsite.configuration.administrator;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TfaJwtTokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TfaJwtTokenProvider tfaJwtTokenProvider;

    public TfaJwtTokenConfigurer(TfaJwtTokenProvider tfaJwtTokenProvider) {
        this.tfaJwtTokenProvider = tfaJwtTokenProvider;
    }

    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new TfaJwtTokenFilter(this.tfaJwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
