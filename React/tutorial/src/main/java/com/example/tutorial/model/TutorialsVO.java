package com.example.tutorial.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document

public class TutorialsVO {
    @Id
    private String id;
    private String title;
    private String description;
    private Boolean published;
}
