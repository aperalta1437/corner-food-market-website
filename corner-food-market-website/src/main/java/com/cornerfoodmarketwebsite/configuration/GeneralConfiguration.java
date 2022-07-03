package com.cornerfoodmarketwebsite.configuration;

import com.cornerfoodmarketwebsite.business.service.utils.LoginRsaKeysStore;
import com.cornerfoodmarketwebsite.business.service.utils.RoleEnum;
import com.cornerfoodmarketwebsite.configuration.utils.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class GeneralConfiguration {
//    private final HashMap<Integer, OriginProperties> originPropertiesMap;
    @Value("${customer.login-rsa-key-size}")
    private int customerLoginRsaKeySize;
    @Value("${administrator.login-rsa-key-size}")
    private int administratorLoginRsaKeySize;
    private final ApplicationProperties properties;

    @Bean
    public HashMap<Integer, ClientOriginProperties> clientOriginProperties() {
        return this.properties.getClientOrigin();
    }

    @Bean
    public LoginRsaKeysStore loginRsaKeysStore() throws NoSuchAlgorithmException {
        return new LoginRsaKeysStore(this.clientOriginProperties(), customerLoginRsaKeySize, administratorLoginRsaKeySize);
    }

    @Bean
    public CustomCorsFilter customCorsFilter() throws NoSuchAlgorithmException {
        return new CustomCorsFilter(this.clientOriginProperties());
    }
}
