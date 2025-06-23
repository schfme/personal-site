package me.schf.personal.controller.posts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import me.schf.personal.data.Post;
import me.schf.personal.data.PostService;
import me.schf.personal.utility.MarkdownToHtmlConverter;

@Controller
public class PostsController {

	private static final String PAGE_TITLE = "posts";
	private static final String BASE_URL = "/" + PAGE_TITLE;

	private final LocalDate today;
	private final PostService postService;
	private final MarkdownToHtmlConverter markdownToHtmlConverter;

	public PostsController(LocalDate today, PostService postService) {
		super();
		this.today = today;
		this.postService = postService;
		this.markdownToHtmlConverter = new MarkdownToHtmlConverter();
	}

	@GetMapping(BASE_URL)
	public String posts(Model model) {
		model.addAttribute("year", today.getYear());
		model.addAttribute("postHeadlines", this.getPostHeadlines());
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
		
		Post post = postService.getAllPostsByTitle().get(link);
		
		if (post == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		model.addAttribute("year", today.getYear());
		model.addAttribute("postHeadline", post.getTitle());
		model.addAttribute("pageTitle", post.getTitle());
		model.addAttribute("pageHeader", "/" + post.getTitle());
		model.addAttribute("markdownContent", markdownToHtmlConverter.convertToHtml(post.getMarkdownText()));

		return "post-detail";
	}
	
	private List<PostHeadline> getPostHeadlines() {
		return postService.getAllPostsByTitle()
			.values()
			.stream()
			.map(PostHeadline::fromPost)
			.toList();
	}
}
