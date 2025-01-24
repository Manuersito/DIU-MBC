package com.example.tutorial.service.impl;

import com.example.tutorial.model.TutorialsDto;
import com.example.tutorial.model.TutorialsVO;
import com.example.tutorial.repository.TutorialsRepository;
import com.example.tutorial.service.TutorialsService;
import com.example.tutorial.util.TutorialsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TutorialsServiceImpl implements TutorialsService {
    @Autowired
    private TutorialsRepository tutorialsRepository;

    @Override
    public List<TutorialsDto> getAllTutorials(){
        List<TutorialsVO> tutorialsVOList = tutorialsRepository.findAll();
        return tutorialsVOList.stream()
                .map(TutorialsMapper::tutorialsMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TutorialsDto> getTutorialById(String id) {
        return Optional.empty();
    }

    @Override
    public List<TutorialsDto> findByTitleContaining(String title) {
        return List.of();
    }

    @Override
    public List<TutorialsDto> findByPublished() {
        return List.of();
    }

    @Override
    public TutorialsDto save(TutorialsDto tutorialDto) {
        TutorialsVO tutorialsVO = TutorialsMapper.tutorialsMapperDtoToEntity(tutorialDto);
        TutorialsVO savedTutorialEntity = tutorialsRepository.save(tutorialsVO);
        return TutorialsMapper.tutorialsMapperEntityToDto(savedTutorialEntity);
    }

    @Override
    public TutorialsDto updateTutorial(TutorialsDto tutorial) {
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
