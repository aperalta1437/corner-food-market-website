package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.business.service.AdministratorUserDetailsService;
import com.cornerfoodmarketwebsite.business.service.CustomerUserDetailsService;
import com.cornerfoodmarketwebsite.controller.utils.LoginProcessIssueEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class AdministratorWebSecurityConfigurerAdapter {
    @Configuration
    @Order(1)
    public static class AdministratorPostTfaWebSecurityConfigurerAdapter  extends WebSecurityConfigurerAdapter {

        @Autowired
        private JwtTokenProvider jwtTokenProvider;

        @Autowired
        private DataSource dataSource;

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
////                .exceptionHandling().accessDeniedPage("/api/admin/login?issue=" + LoginProcessIssueEnum.EXPIRED_SESSION.name());    // todo fix this
//        http.csrf().disable().sessionManagement().disable();
////        sessionCreationPolicy(SessionCreationPolicy.NEVER);
////        http.requestMatcher(new AntPathRequestMatcher("/admin/account/**")).authorizeRequests().anyRequest().authenticated();

            http.cors().and().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                    .authorizeRequests().antMatchers("/**").permitAll()
                    .requestMatcher(new AntPathRequestMatcher("/api/admin/account/**")).authorizeRequests().anyRequest().authenticated()
//                    .antMatcher("/api/admin/account/**").authorizeRequests().anyRequest().authenticated()
//                    .authorizeRequests().antMatchers("/api/admin/account/**").authenticated()
                    .and()
                    .logout().permitAll()
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                    .logoutUrl("/api/admin/account/logout")
                    .deleteCookies("JSESSIONID");

            http.apply(new JwtTokenConfigurer(this.jwtTokenProvider));
        }
    }

    @Configuration
    @Order(2)
    public static class AdministratorPreTfaWebSecurityConfigurerAdapter  extends WebSecurityConfigurerAdapter {

        @Autowired
        private TfaJwtTokenProvider tfaJwtTokenProvider;

        @Autowired
        private DataSource dataSource;

        @Bean
        public UserDetailsService administratorPreTfaUserDetailsService() {
            return new AdministratorUserDetailsService();
        }

        @Override
        @Bean(name = "administratorPreTfaAuthenticationManagerBean")
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

//        @Bean
//        public JavaMailSender getJavaMailSender() {
//            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//            mailSender.setHost("smtp.gmail.com");
//            mailSender.setPort(587);
//
//            mailSender.setUsername("cornerfoodmarket.pss@gmail.com");
//            mailSender.setPassword("npbiratssavteinh");
//
//            Properties props = mailSender.getJavaMailProperties();
//            props.put("mail.transport.protocol", "smtp");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.debug", "true");
//
//            return mailSender;
//        }


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
////                .exceptionHandling().accessDeniedPage("/api/admin/login?issue=" + LoginProcessIssueEnum.EXPIRED_SESSION.name());    // todo fix this
//        http.csrf().disable().sessionManagement().disable();
////        sessionCreationPolicy(SessionCreationPolicy.NEVER);
////        http.requestMatcher(new AntPathRequestMatcher("/admin/account/**")).authorizeRequests().anyRequest().authenticated();

            http.cors().and().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                    .authorizeRequests().antMatchers("/**").permitAll()
                    .antMatcher("/api/admin/login/tfa-post-authenticate/**").authorizeRequests().anyRequest().authenticated()
//                    .authorizeRequests().antMatchers("/api/admin/login/tfa-post-authenticate/**").authenticated()
                    .and()
                    .logout().permitAll()
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                    .logoutUrl("/api/admin/login/tfa-post-authenticate/logout");
//                    .deleteCookies("JSESSIONID");

            http.apply(new TfaJwtTokenConfigurer(this.tfaJwtTokenProvider));
        }
    }

//    @Configuration
////@EnableWebSecurity
//    @Order(3)
//    public static class CustomerConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//        @Autowired
//        private DataSource dataSource;
//
//        @Bean
//        public UserDetailsService customerUserDetailsService() {
//            return new CustomerUserDetailsService();
//        }
//
//        @Primary
//        @Override
//        @Bean(name = "customerAuthenticationManagerBean")
//        public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//        }
//
//        @Bean
//        public BCryptPasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//
//        @Bean
//        public DaoAuthenticationProvider authenticationProvider() {
//            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//            authProvider.setUserDetailsService(this.customerUserDetailsService());
//            authProvider.setPasswordEncoder(this.passwordEncoder());
//
//            return authProvider;
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.authenticationProvider(this.authenticationProvider());
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
////                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
////        http.authorizeRequests().antMatchers("/account/**").authenticated()
//                    .antMatcher("/account/**").authorizeRequests().anyRequest().authenticated()
//
//                    .and()
//                    .formLogin()
//                    .loginPage("/login").permitAll()
////                    .loginProcessingUrl("/login")
//                    .usernameParameter("email")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/account").permitAll()
//                    .failureForwardUrl("/login?failed=true")
//                    .and()
//                    .logout()
//                    .logoutSuccessUrl("/login")
//                    .and()
//                    .exceptionHandling().accessDeniedPage("/login?issue=" + LoginProcessIssueEnum.EXPIRED_SESSION.name());
//        }
//    }
}
