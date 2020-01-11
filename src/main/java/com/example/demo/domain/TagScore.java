package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class TagScore {
    private String tagName;
    private Double score;
}
