package com.example.tutorial.service;
import com.example.tutorial.model.TutorialsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TutorialsService {
    List<TutorialsDto> getAllTutorials(); // Funciona

    Optional<TutorialsDto> getTutorialById(String id); // Funciona

    List<TutorialsDto> findByTitleContaining(String title);

    List<TutorialsDto> findByPublished(); // Funciona

    TutorialsDto save(TutorialsDto tutorial); // Funciona

    TutorialsDto updateTutorial(TutorialsDto tutorial); // Funciona

    ResponseEntity deleteTutorial(String id); // Funciona

    ResponseEntity deleteAllTutorials(); // Funciona
}
