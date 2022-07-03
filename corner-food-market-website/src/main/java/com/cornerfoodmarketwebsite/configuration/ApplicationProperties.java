package com.cornerfoodmarketwebsite.configuration;

import com.cornerfoodmarketwebsite.configuration.utils.ClientOriginProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;

@ConfigurationProperties
@Setter
@Getter
public class ApplicationProperties {
    private HashMap<Integer, ClientOriginProperties> clientOrigin;
}
