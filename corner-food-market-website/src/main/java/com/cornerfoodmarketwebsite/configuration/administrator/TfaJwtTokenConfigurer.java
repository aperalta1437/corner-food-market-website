package com.cornerfoodmarketwebsite.configuration.administrator;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class TfaJwtTokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TfaJwtTokenProvider tfaJwtTokenProvider;

    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new TfaJwtTokenFilter(this.tfaJwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
