package me.schf.personal.controller.home;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import me.schf.personal.config.AppConfig;
import me.schf.personal.config.aws.AwsConfig.ParameterRetriever;
import me.schf.personal.config.site.SiteProperties;

@Controller
public class HomeController {

	private final LocalDate today;
	private final SiteProperties siteProperties;
	private final AppConfig appConfig;
	private final ParameterRetriever awsParameterRetriever;

	public HomeController(LocalDate today, SiteProperties siteProperties, AppConfig appConfig,
			ParameterRetriever awsParameterRetriever) {
		super();
		this.today = today;
		this.siteProperties = siteProperties;
		this.appConfig = appConfig;
		this.awsParameterRetriever = awsParameterRetriever;
	}

	@GetMapping("/")
	public String home(Model model) {
		String emailAddress = awsParameterRetriever.getParameter(appConfig.getParameterNames().getEmailAddressName());
		model.addAttribute("emailAddress", emailAddress);

		model.addAttribute("year", today.getYear());
		model.addAttribute("githubLink", siteProperties.getExternalLinkConfig().getGitHubLink());
		model.addAttribute("instagramLink", siteProperties.getExternalLinkConfig().getIgLink());
		model.addAttribute("linkedinLink", siteProperties.getExternalLinkConfig().getLinkedinLink());
		model.addAttribute("resumeLink", siteProperties.getExternalLinkConfig().getResumeLink());
		model.addAttribute("cubeeLink", siteProperties.getExternalLinkConfig().getCubeeLink());
		model.addAttribute("aiTunnelLink", siteProperties.getExternalLinkConfig().getAiTunnelLink());


		return "home";
	}
}
