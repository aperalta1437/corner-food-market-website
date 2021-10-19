package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.business.service.AdministratorUserDetailsService;
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

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class AdministratorWebSecurityConfigurerAdapter {
    @Configuration
    @Order(1)
    public static class AdministratorAccountWebSecurityConfigurerAdapter  extends WebSecurityConfigurerAdapter {

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
//        http.cors().configurationSource(request -> {
//            var cors = new CorsConfiguration();
//            cors.setAllowedOrigins(List.of("*"));
//            cors.setAllowedMethods(List.of("*"));
//            cors.setAllowedHeaders(List.of("*"));
//            return cors;
//        }).and()
////        http.cors().and()
////            .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint()).and()
//            .antMatcher("/api/admin/account/**").authorizeRequests().anyRequest().authenticated();
////                .and()
////                .formLogin()
////                .loginPage("/admin/login").permitAll()
////                .loginProcessingUrl("/api/admin/login/authenticate")
////                .usernameParameter("email")
////                .passwordParameter("password")
////                .defaultSuccessUrl("/api/admin/account").permitAll()
////                .failureForwardUrl("/api/admin/login?failed=true")
////                .and()
////                .logout()
////                .logoutSuccessUrl("/api/admin/login");
////                .exceptionHandling().accessDeniedPage("/api/admin/login?issue=" + LoginProcessIssue.EXPIRED_SESSION.name());    // todo fix this
//        http.csrf().disable().sessionManagement().disable();
////        sessionCreationPolicy(SessionCreationPolicy.NEVER);
////        http.requestMatcher(new AntPathRequestMatcher("/admin/account/**")).authorizeRequests().anyRequest().authenticated();

            http.cors().and().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint()).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                    .authorizeRequests().antMatchers("/**").permitAll()
                    .authorizeRequests().antMatchers("/api/admin/account/**").authenticated()
                    .and()
                            .logout().deleteCookies("JSESSIONID");

            http.apply(new JwtTokenConfigurer(this.jwtTokenProvider));
        }
    }
}
