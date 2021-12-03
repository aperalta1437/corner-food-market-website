//package com.cornerfoodmarketwebsite.configuration.customer;
//
//import com.cornerfoodmarketwebsite.business.service.CustomerUserDetailsService;
//import com.cornerfoodmarketwebsite.controller.utils.LoginProcessIssueEnum;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//
//@Configuration
////@EnableWebSecurity
//@Order(-1)
//public class CustomerConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public UserDetailsService customerUserDetailsService() {
//        return new CustomerUserDetailsService();
//    }
//
//    @Primary
//    @Override
//    @Bean(name = "customerAuthenticationManagerBean")
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(this.customerUserDetailsService());
//        authProvider.setPasswordEncoder(this.passwordEncoder());
//
//        return authProvider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(this.authenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
////                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
////        http.authorizeRequests().antMatchers("/account/**").authenticated()
//                .antMatcher("/account/**").authorizeRequests().anyRequest().authenticated()
//
//        .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .loginProcessingUrl("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/account").permitAll()
//                .failureForwardUrl("/login?failed=true")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login")
//                .and()
//                .exceptionHandling().accessDeniedPage("/login?issue=" + LoginProcessIssueEnum.EXPIRED_SESSION.name());
//    }
//}
//
//
