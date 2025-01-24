package com.example.tutorial.model;

import lombok.*;
import org.springframework.data.annotation.Id;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TutorialsDto {
    @Id
    private String id;
    private String title;
    private String description;
    private Boolean published;
}
