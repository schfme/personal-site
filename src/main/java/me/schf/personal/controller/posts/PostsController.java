package me.schf.personal.controller.posts;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import me.schf.personal.service.PostsService;
import me.schf.personal.service.domain.PostDto;
import me.schf.personal.utility.MarkdownToHtmlConverter;

@Controller
public class PostsController {

	private static final String PAGE_TITLE = "posts";
	private static final String BASE_URL = "/" + PAGE_TITLE;

	private final LocalDate today;
	private final PostsService postsService;

	private final MarkdownToHtmlConverter markdownToHtmlConverter;
	
	public PostsController(LocalDate today, PostsService postsService) {
		super();
		this.today = today;
		this.postsService = postsService;
		this.markdownToHtmlConverter = new MarkdownToHtmlConverter();
	}

	@GetMapping(BASE_URL)
	public String posts(Model model) {
		model.addAttribute("year", today.getYear());
		model.addAttribute("postHeadlines", postsService.getRecentPostHeadlines());
		model.addAttribute("baseUrl", BASE_URL);
		model.addAttribute("pageTitle", PAGE_TITLE);
		model.addAttribute("pageHeader", BASE_URL);
		return "posts";
	}

	@GetMapping(BASE_URL + "/{link}")
	public String postDetail(@PathVariable("link") String link, Model model) {
		return detail(link, model);
	}

	public String detail(String link, Model model) {
		
		PostDto postDto = postsService.getPostByTitle(link);
		
		if (postDto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		model.addAttribute("year", today.getYear());
		model.addAttribute("postHeadline", postDto.getPostHeadline().getTitle());
		model.addAttribute("pageTitle", postDto.getPostHeadline().getTitle());
		model.addAttribute("pageHeader", "/" + postDto.getPostHeadline().getTitle());
		model.addAttribute("markdownContent", markdownToHtmlConverter.convertToHtml(postDto.getMarkdownText()));

		return "post-detail";
	}

}
