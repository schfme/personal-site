package me.schf.personal.controller.content.blog;

import java.time.LocalDate;

import me.schf.personal.controller.content.Content;

public record BlogEntry(String entryName, LocalDate entryDate, String link) implements Content {

	@Override
	public String getName() {
		return this.entryName;
	}

	@Override
	public LocalDate getDate() {
		return this.entryDate;
	}

	@Override
	public String getLink() {
		return this.link;
	}

}
