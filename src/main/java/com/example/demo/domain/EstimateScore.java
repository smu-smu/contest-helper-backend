package com.example.demo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EstimateScore {

    @NotNull
    private String accountId;
    @NotNull
    private Double score = 0.0;

}
