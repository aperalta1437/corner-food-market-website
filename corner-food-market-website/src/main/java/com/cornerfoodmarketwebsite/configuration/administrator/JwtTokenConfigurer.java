package com.cornerfoodmarketwebsite.configuration.administrator;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtTokenFilter(this.jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
