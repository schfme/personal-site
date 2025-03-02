package me.schf.personal.controller.content.blog;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import me.schf.personal.config.SiteProperties;
import me.schf.personal.controller.content.AbstractContentController;

@Controller
public class BlogController extends AbstractContentController<BlogEntry> {

	private static final String BASE_URL = "/blog";

	public BlogController(LocalDate today, List<BlogEntry> projectsList, SiteProperties siteProperties) {
		super(today, projectsList, siteProperties, BASE_URL);
	}

	@GetMapping(BASE_URL)
	public String blogEntries(Model model) {
		return list(model);
	}

	@GetMapping(BASE_URL + "/{link}")
	public String blogEntry(@PathVariable("link") String link, Model model) {
		return detail(link, model);
	}
}
