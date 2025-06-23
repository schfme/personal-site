package me.schf.personal.controller.resume;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import me.schf.personal.config.ExternalLinkConfig;
import me.schf.personal.config.SiteProperties;

@Controller
public class ResumeRedirectController {

	private final ExternalLinkConfig externalLinkConfig;

	public ResumeRedirectController(SiteProperties siteProperties) {
		super();
		this.externalLinkConfig = siteProperties.getExternalLinkConfig();
	}

	@GetMapping("/resume")
	public RedirectView redirectToResume() {
		return new RedirectView(externalLinkConfig.getResumeLink());
	}
}
