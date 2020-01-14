package com.babay15.perprom.web;

import com.babay15.perprom.domain.Project;
import com.babay15.perprom.services.MapValidationErrorService;
import com.babay15.perprom.services.NotFoundErrorService;
import com.babay15.perprom.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private NotFoundErrorService notFoundErrorService;

    @PostMapping("")
    private ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        if(result.hasErrors()){
            return mapValidationErrorService.MapValidationService(result);
        }

        Project newProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    private ResponseEntity<?> findByProjectIdentifier(@PathVariable String projectIdentifier){
        Project project = projectService.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project!=null){
            return new ResponseEntity<Project>(project, HttpStatus.OK);
        }

        return notFoundErrorService.NotFoundErrorService(projectIdentifier.toUpperCase());
    }

    @GetMapping("/all")
    private ResponseEntity<?> findAll(){
        Iterable allProject = projectService.findAll();

        return new ResponseEntity<Iterable<Project>>(allProject, HttpStatus.OK);
    }
}
