package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class Estimate {

    @NotNull
    private String accountId;
    @NotNull
    private String teamId;

}
