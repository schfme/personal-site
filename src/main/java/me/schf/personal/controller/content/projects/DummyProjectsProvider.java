package me.schf.personal.controller.content.projects;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import me.schf.personal.controller.content.ContentProvider;

@Component
public class DummyProjectsProvider implements ContentProvider<Project> {

	@Override
	public List<Project> getContent() {
		Project fakeProject1 = new Project("placeholder project 1", LocalDate.now(), "placeholder-project-one");
		Project fakeProject2 = new Project("placehodler project 2", LocalDate.now().minusYears(20), "placeholder-project-two");

		return Arrays.asList(fakeProject1, fakeProject2);
	}

}
