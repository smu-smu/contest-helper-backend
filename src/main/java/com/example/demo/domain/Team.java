package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Team {
    @Id
    private String teamId;
    private String teamName;
    //private Competetion compName;

    private List<Account> teammates = new ArrayList<>();

    public void addTeammate(Account teammate) {
        this.teammates.add(teammate);
    }


}
