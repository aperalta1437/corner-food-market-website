package com.cornerfoodmarketwebsite;

import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepositoryImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImplementation.class)
public class CornerFoodMarketWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CornerFoodMarketWebsiteApplication.class, args);
	}

}
