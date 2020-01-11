package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class Participant {
    @Id @NotNull
    private String accountId;
    private String teamId;
    private String status="waiting";
}
