package com.cornerfoodmarketwebsite.configuration;

import com.cornerfoodmarketwebsite.business.service.utils.LoginRsaKeysStore;
import com.cornerfoodmarketwebsite.business.service.utils.RoleEnum;
import com.cornerfoodmarketwebsite.configuration.utils.InvalidOriginPropertyException;
import com.cornerfoodmarketwebsite.configuration.utils.InvalidRoleException;
import com.cornerfoodmarketwebsite.configuration.utils.OriginProperties;
import com.cornerfoodmarketwebsite.configuration.utils.OriginPropertyEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class GeneralConfiguration {
//    @Value("#{'${pss.clients-domain-names}'.split(',')}")
//    private List<String> pssClientsDomainNames;
    private final HashMap<Integer, OriginProperties> originPropertiesMap;
    @Value("${customer.login-rsa-key-size}")
    private int customerLoginRsaKeySize;
    @Value("${administrator.login-rsa-key-size}")
    private int administratorLoginRsaKeySize;

    public GeneralConfiguration(@Value("#{${client-origin-properties-map}}") HashMap<Integer, String> originPropertiesStringMap) throws InvalidOriginPropertyException, InvalidRoleException {
        HashMap<Integer, OriginProperties> originPropertiesMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : originPropertiesStringMap.entrySet()) {
            String[] properties = entry.getValue().split("\\|");    // Split by "|".
            String origin = null;
            String storeName = null;
            boolean isAllowed = false;
            RoleEnum roleEnum = null;
            for (String property: properties) {
                String[] propertyNameAndValue = property.split("->");
                if (Objects.equals(propertyNameAndValue[0], OriginPropertyEnum.ORIGIN.toString())) {
                    origin = propertyNameAndValue[1];
                } else if (Objects.equals(propertyNameAndValue[0], OriginPropertyEnum.STORE_NAME.toString())) {
                    storeName = propertyNameAndValue[1];
                } else if (Objects.equals(propertyNameAndValue[0], OriginPropertyEnum.IS_ALLOWED.toString())) {
                    isAllowed = Boolean.parseBoolean(propertyNameAndValue[1]);
                } else if (Objects.equals(propertyNameAndValue[0], OriginPropertyEnum.ROLE.toString())) {
                    if (RoleEnum.CUSTOMER.toString().equalsIgnoreCase(propertyNameAndValue[1])) {
                        roleEnum = RoleEnum.CUSTOMER;
                    } else if (RoleEnum.ADMINISTRATOR.toString().equalsIgnoreCase(propertyNameAndValue[1])) {
                        roleEnum = RoleEnum.ADMINISTRATOR;
                    } else {
                        throw new InvalidRoleException(propertyNameAndValue[1]);
                    }
                } else {
                    throw new InvalidOriginPropertyException(propertyNameAndValue[0]);
                }
            }
            assert origin != null;
            assert storeName != null;
            assert roleEnum != null;
            originPropertiesMap.put(entry.getKey(), new OriginProperties(origin, storeName, isAllowed, roleEnum));
        }
        this.originPropertiesMap = originPropertiesMap;
    }

    @Bean
    public LoginRsaKeysStore loginRsaKeysStore() throws NoSuchAlgorithmException {
        return new LoginRsaKeysStore(originPropertiesMap, customerLoginRsaKeySize, administratorLoginRsaKeySize);
    }

    @Bean
    public CustomCorsFilter customCorsFilter() throws NoSuchAlgorithmException {
        return new CustomCorsFilter(originPropertiesMap);
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
//        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
