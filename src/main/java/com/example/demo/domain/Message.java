package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Message {
    private String title;
    private String content;
}
