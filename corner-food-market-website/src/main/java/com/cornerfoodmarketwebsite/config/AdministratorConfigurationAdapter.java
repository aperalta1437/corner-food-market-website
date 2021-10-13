package com.cornerfoodmarketwebsite.config;

import com.cornerfoodmarketwebsite.business.service.AdministratorUserDetailsService;
import com.cornerfoodmarketwebsite.controller.utils.LoginProcessIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@Order(2)
public class AdministratorConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService administratorUserDetailsService() {
        return new AdministratorUserDetailsService();
    }

    @Override
    @Bean(name = "administratorAuthenticationManagerBean")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public BCryptPasswordEncoder administratorPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider administratorAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.administratorUserDetailsService());
        authProvider.setPasswordEncoder(this.administratorPasswordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.administratorAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
        http.requestMatcher(new AntPathRequestMatcher("/admin/account/**")).csrf().disable().authorizeRequests().antMatchers("/admin/account/**").authenticated()
                .and()
                .formLogin()
//                .loginPage("/admin/login").permitAll()
                .loginProcessingUrl("/admin/login/authenticate")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/account").permitAll()
                .failureForwardUrl("/admin/login?failed=true")
                .and()
                .logout()
                .logoutSuccessUrl("/admin/login")
                .and()
                .exceptionHandling().accessDeniedPage("/admin/login?issue=" + LoginProcessIssue.EXPIRED_SESSION.name());    // todo fix this
//        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.requestMatcher(new AntPathRequestMatcher("/admin/account/**")).authorizeRequests().anyRequest().authenticated();

        http.apply(new JwtTokenConfigurer(this.jwtTokenProvider));
    }
}
