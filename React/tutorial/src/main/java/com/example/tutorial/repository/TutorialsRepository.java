package com.example.tutorial.repository;

import com.example.tutorial.model.TutorialsVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TutorialsRepository extends MongoRepository<TutorialsVO,String> {
    List<TutorialsVO> findByPublishedTrue();
    List<TutorialsVO> findAll();
    Optional<TutorialsVO> getTutorialsById();
    List<TutorialsVO> findByTitleContaining(String title);
    List<TutorialsVO> findByPublished(boolean published);

}
