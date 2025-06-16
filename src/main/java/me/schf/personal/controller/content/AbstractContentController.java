package me.schf.personal.controller.content;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractContentController<T extends Content> {

	private final LocalDate today;
	private final List<T> contents;
	private final String baseUrl;

	protected AbstractContentController(LocalDate today, List<T> contents, String baseUrl) {
		this.today = today;
		this.contents = contents;
		this.baseUrl = baseUrl;
	}

	public String list(Model model) {
		model.addAttribute("year", today.getYear());
		model.addAttribute("contents", contents);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("pageTitle", baseUrl);
		model.addAttribute("pageHeader", baseUrl);
		return "content";
	}

	public String detail(String link, Model model) {
		T content = contents.stream().filter(c -> c.getLink().equals(link)).findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		model.addAttribute("year", today.getYear());
		model.addAttribute("content", content);
		model.addAttribute("pageTitle", content.getName());
		model.addAttribute("pageHeader", content.getName());
		return "content-detail";
	}
}
