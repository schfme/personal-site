package me.schf.personal.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.schf.personal.service.HeadlineProvider;
import me.schf.personal.service.ProjectProvider;
import me.schf.personal.service.domain.Project;

@Configuration
public class ContentConfig {

	@Bean
	LocalDate today() {
		return LocalDate.now();
	}

	@Bean
	List<String> tickerItems(HeadlineProvider headlineProvider, LocalDate today,
			@Value("${headline.years.to.subtract}") int yearsToSubtract) {
		LocalDate headlineDate = today.minusYears(yearsToSubtract);
		return headlineProvider.getHeadlinesOn(headlineDate);
	}

	@Bean
	List<Project> projectsList(ProjectProvider projectProvider) {
		return projectProvider.getProjects();
	}

}
