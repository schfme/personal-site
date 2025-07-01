package me.schf.personal.config.content;

import java.time.LocalDate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.schf.personal.config.site.SiteProperties;

@Configuration
public class ContentConfig {

	@Bean
	LocalDate today() {
		return LocalDate.now();
	}

	@Bean
	@ConfigurationProperties("site-properties")
	SiteProperties siteProperties() {
		return new SiteProperties();
	}
	
}
