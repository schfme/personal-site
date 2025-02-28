package me.schf.personal.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MusicController {

	private LocalDate today;

	public MusicController(LocalDate today) {
		super();
		this.today = today;
	}

	@GetMapping("/music")
	public String music(Model model) {
		model.addAttribute("year", today.getYear());
		return "music";
	}
}
