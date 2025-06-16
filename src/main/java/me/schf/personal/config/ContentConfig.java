package me.schf.personal.config;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.schf.personal.controller.content.Content;
import me.schf.personal.controller.content.ContentProvider;
import me.schf.personal.controller.content.projects.Project;

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
	@Bean
	List<Project> projectsList(ContentProvider<Project> projectProvider) {
		List<Project> projectList = projectProvider.getContent();
		projectList.sort(Comparator.comparing(Content::getDate).reversed());
		return projectList;
	}

}
