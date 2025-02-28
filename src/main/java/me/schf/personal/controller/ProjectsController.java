package me.schf.personal.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import me.schf.personal.service.domain.Project;

@Controller
public class ProjectsController {

    private LocalDate today;
    private List<Project> projectsList;

    public ProjectsController(LocalDate today, List<Project> projectsList) {
        this.today = today;
        this.projectsList = projectsList;
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("year", today.getYear());
        model.addAttribute("projects", projectsList);
        return "projects";
    }

    @GetMapping("/projects/{projectName}")
    public String projectPage(@PathVariable String projectName, Model model) {
        Optional<Project> projectOptional = projectsList.stream()
            .filter(p -> p.projectName().equals(projectName))
            .findFirst();

        if (!projectOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("year", today.getYear());
        model.addAttribute("project", projectOptional.get());
        
        return "project";
    }
}
