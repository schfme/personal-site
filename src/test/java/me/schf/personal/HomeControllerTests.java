package me.schf.personal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.schf.personal.config.SiteProperties;
import me.schf.personal.controller.content.ContentProvider;
import me.schf.personal.controller.content.projects.Project;

@SpringBootTest
class HomeControllerTests {
	
	@Autowired
	ContentProvider<Project> projectProvider;
	
	@Autowired
	SiteProperties siteProperties;

	@Test
	void test_contentProviders_projects_hasProjects() {
		List<Project> projects = projectProvider.getContent();
		assertNotNull(projects, "Project list should not be null.");
		assertFalse(projects.isEmpty(), "Project list should not be empty.");
	}
	
	@Test
	void test_links_ig() {
		assertEquals("https://www.instagram.com/schf.me", siteProperties.getIgLink());
	}
	
	@Test
	void test_links_email() {
		assertEquals("connect@schf.me", siteProperties.getEmailAddress());
	}
	
	@Test
	void test_links_github() {
		assertEquals("https://github.com/schfme", siteProperties.getGitHubLink());
	}

}
