package me.schf.personal.controller.content.projects;

import java.time.LocalDate;

import me.schf.personal.controller.content.Content;

public record Project(String projectName, LocalDate projectDate, String link) implements Content {

	@Override
	public String getName() {
		return this.projectName;
	}

	@Override
	public LocalDate getDate() {
		return this.projectDate;
	}

	@Override
	public String getLink() {
		return this.link;
	}

}
