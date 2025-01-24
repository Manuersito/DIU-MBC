package com.example.tutorial.controller.impl;


import com.example.tutorial.controller.TutorialsAPI;
import com.example.tutorial.model.TutorialsDto;
import com.example.tutorial.service.TutorialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TutorialsController implements TutorialsAPI {
    @Autowired
    private TutorialsService tutorialsService;
    //@Autowired //private TutorialsRepository tutorialsRepository; @Override
    @GetMapping("/tutorials")
    public List<TutorialsDto> getAllTutorials(){return tutorialsService.getAllTutorials();}


    @Override
    public Optional<TutorialsDto> getTutorialById(String id) {
        return Optional.empty();
    }

    @Override
    public List<TutorialsDto> findByTitleContaining(String id) {
        return List.of();
    }

    @Override
    public List<TutorialsDto> findByPublished() {
        return List.of();
    }

    @Override
    @CrossOrigin //F!* CORS@PostMapping("/tutorials")
    public TutorialsDto save(@RequestBody TutorialsDto tutorialDto) {
        return tutorialsService.save(tutorialDto);
    }

    @Override
    public TutorialsDto updateTutorial(TutorialsDto tutorial, String id) {
        return null;
    }

    @Override
    public ResponseEntity deleteTutorial(String id) {
        return null;
    }

    @Override
    public ResponseEntity deleteAllTutorials() {
        return null;
    }
}
