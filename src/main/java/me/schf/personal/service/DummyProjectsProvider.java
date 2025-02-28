package me.schf.personal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import me.schf.personal.service.domain.Project;

@Component
public class DummyProjectsProvider implements ProjectProvider {

	@Override
	public List<Project> getProjects() {

		Project fakeProject1 = new Project("a-cool-project", LocalDate.now());
		Project fakeProject2 = new Project("another-cool-project", LocalDate.now().minusYears(20));

		return List.of(fakeProject1, fakeProject2);

	}

}
