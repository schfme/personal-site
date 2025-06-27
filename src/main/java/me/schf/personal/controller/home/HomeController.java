package me.schf.personal.controller.home;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import me.schf.personal.config.site.SiteProperties;

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
		model.addAttribute("emailAddress", siteProperties.getExternalLinkConfig().getEmailAddress());
		model.addAttribute("githubLink", siteProperties.getExternalLinkConfig().getGitHubLink());
		model.addAttribute("instagramLink", siteProperties.getExternalLinkConfig().getIgLink());
		model.addAttribute("linkedinLink", siteProperties.getExternalLinkConfig().getLinkedinLink());
		model.addAttribute("resumeLink", siteProperties.getExternalLinkConfig().getResumeLink());

		return "home";
	}
}
