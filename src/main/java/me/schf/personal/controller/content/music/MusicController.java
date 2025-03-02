package me.schf.personal.controller.content.music;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import me.schf.personal.config.SiteProperties;
import me.schf.personal.controller.content.AbstractContentController;

@Controller
public class MusicController extends AbstractContentController<Music> {

	private static final String BASE_URL = "/music";

	public MusicController(LocalDate today, List<Music> musicList, SiteProperties siteProperties) {
		super(today, musicList, siteProperties, BASE_URL);
	}

	@GetMapping(BASE_URL)
	public String musicList(Model model) {
		return list(model);
	}

	@GetMapping(BASE_URL + "/{link}")
	public String musicDetail(@PathVariable("link") String link, Model model) {
		return detail(link, model);
	}
}
