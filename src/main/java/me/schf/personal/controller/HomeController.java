package me.schf.personal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private List<String> tickerItems;
	private LocalDate today;

	public HomeController(List<String> tickerItems, LocalDate today) {
		super();
		this.tickerItems = tickerItems;
		this.today = today;
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("year", today.getYear());
		model.addAttribute("tickerItems", tickerItems);
		return "index";
	}
}
