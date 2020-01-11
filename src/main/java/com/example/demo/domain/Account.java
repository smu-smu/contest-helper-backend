package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Account {
    @Id
    @NotNull
    private String userId;
    private String password;
    private String name;


    private List<String> favorites = new ArrayList<>();
    private List<String> profiles = new ArrayList<>();

    @Field("tagScores")
    private List<TagScore> tagScores = new ArrayList<>();

    @Field("messages")
    private List<Message> messages = new ArrayList<>();
}
