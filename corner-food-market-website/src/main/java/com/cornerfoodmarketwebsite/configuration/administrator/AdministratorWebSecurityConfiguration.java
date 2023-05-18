package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.business.service.AdministratorUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class AdministratorWebSecurityConfiguration {
    @Configuration
    @Order(1)
    @RequiredArgsConstructor
    public static class AdministratorPostTfaWebSecurityConfiguration  extends WebSecurityConfigurerAdapter {

        private final AccessTokenProvider accessTokenProvider;
        private final DataSource dataSource;

        @Bean
        public UserDetailsService administratorPostTfaUserDetailsService() {
            return new AdministratorUserDetailsService();
        }

        @Override
        @Bean(name = "administratorPostTfaAuthenticationManagerBean")
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public BCryptPasswordEncoder administratorPostTfaPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider administratorPostTfaAuthenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(this.administratorPostTfaUserDetailsService());
            authProvider.setPasswordEncoder(this.administratorPostTfaPasswordEncoder());

            return authProvider;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(this.administratorPostTfaAuthenticationProvider());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // REMINDER: DO NOT include context path
            http.cors().disable().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                    .authorizeRequests().antMatchers("/**").permitAll()
                    .requestMatcher(new AntPathRequestMatcher("/admin/account/**")).authorizeRequests().anyRequest().authenticated();
//                    .antMatcher("/api/admin/account/**").authorizeRequests().anyRequest().authenticated()
//                    .authorizeRequests().antMatchers("/api/admin/account/**").authenticated()
//                    .and()
//                    .logout().permitAll()
//                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
//                    .logoutUrl("/admin/account/logout")
//                    .deleteCookies("JSESSIONID");

            http.apply(new AccessTokenConfigurer(this.accessTokenProvider));
        }
    }

    @Configuration
    @Order(2)
    @RequiredArgsConstructor
    public static class AdministratorPreTfaWebSecurityConfiguration  extends WebSecurityConfigurerAdapter {

        private final TfaAccessTokenProvider tfaAccessTokenProvider;
        private final DataSource dataSource;

        @Bean
        public UserDetailsService administratorPreTfaUserDetailsService() {
            return new AdministratorUserDetailsService();
        }

        @Override
        @Bean(name = "administratorPreTfaAuthenticationManagerBean")
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public BCryptPasswordEncoder administratorPreTfaPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider administratorPreTfaAuthenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(this.administratorPreTfaUserDetailsService());
            authProvider.setPasswordEncoder(this.administratorPreTfaPasswordEncoder());

            return authProvider;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(this.administratorPreTfaAuthenticationProvider());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // REMINDER: DO NOT include context path
            http.cors().disable().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                    .authorizeRequests().antMatchers("/**").permitAll()
                    .antMatcher("/admin/login/tfa-post-authenticate/**").authorizeRequests().anyRequest().authenticated();
//                    .authorizeRequests().antMatchers("/api/admin/login/tfa-post-authenticate/**").authenticated()
//                    .and()
//                    .logout().permitAll()
//                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
//                    .logoutUrl("/admin/login/tfa-post-authenticate/logout");
//                    .deleteCookies("JSESSIONID");

            http.apply(new TfaAccessTokenConfigurer(this.tfaAccessTokenProvider));
        }
    }

    @Configuration
    @Order(3)
    @RequiredArgsConstructor
    public static class AdministratorRefreshWebSecurityConfiguration  extends WebSecurityConfigurerAdapter {

        private final RefreshTokenProvider refreshTokenProvider;
        private final AccessTokenProvider accessTokenProvider;
        private final DataSource dataSource;

        @Bean
        public UserDetailsService administratorRefreshUserDetailsService() {
            return new AdministratorUserDetailsService();
        }

        @Override
        @Bean(name = "administratorRefreshAuthenticationManagerBean")
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public BCryptPasswordEncoder administratorRefreshPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider administratorRefreshAuthenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(this.administratorRefreshUserDetailsService());
            authProvider.setPasswordEncoder(this.administratorRefreshPasswordEncoder());

            return authProvider;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(this.administratorRefreshAuthenticationProvider());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // REMINDER: DO NOT include context path
            http.cors().disable().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                    .authorizeRequests().antMatchers("/**").permitAll()
                    .antMatcher("/admin/login/re-authenticate/**").authorizeRequests().anyRequest().authenticated();
//                    .authorizeRequests().antMatchers("/api/admin/login/tfa-post-authenticate/**").authenticated()
//                    .and()
//                    .logout().permitAll()
//                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
//                    .logoutUrl("/admin/login/tfa-post-authenticate/logout");
//                    .deleteCookies("JSESSIONID");

            http.apply(new RefreshTokenConfigurer(refreshTokenProvider, accessTokenProvider));
        }
    }
}
