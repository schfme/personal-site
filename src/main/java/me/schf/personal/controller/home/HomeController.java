package me.schf.personal.controller.home;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import me.schf.personal.config.SiteProperties;

@Controller
public class HomeController {

	private final LocalDate today;
	private final SiteProperties siteProperties;

	public HomeController(LocalDate today, SiteProperties siteProperties) {
		super();
		this.today = today;
		this.siteProperties = siteProperties;
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("year", today.getYear());
		model.addAttribute("instagramLink", siteProperties.getIgLink());
		model.addAttribute("githubLink", siteProperties.getGitHubLink());
		model.addAttribute("emailAddress", siteProperties.getEmailAddress());
		model.addAttribute("domain", siteProperties.getDomainName());

		return "home";
	}
}
