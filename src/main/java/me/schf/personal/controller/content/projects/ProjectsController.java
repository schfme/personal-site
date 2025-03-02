package me.schf.personal.controller.content.projects;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import me.schf.personal.config.SiteProperties;
import me.schf.personal.controller.content.AbstractContentController;

@Controller
public class ProjectsController extends AbstractContentController<Project> {

	private static final String BASE_URL = "/projects";

	public ProjectsController(LocalDate today, List<Project> projectsList, SiteProperties siteProperties) {
		super(today, projectsList, siteProperties, BASE_URL);
	}

	@GetMapping(BASE_URL)
	public String projectsList(Model model) {
		return list(model);
	}

	@GetMapping(BASE_URL + "/{link}")
	public String projectDetail(@PathVariable("link") String link, Model model) {
		return detail(link, model);
	}
}
