package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "teamSignUpRequest")
public class TeamSignUpRequest {
    private String accountId;
    private String teamId;
    private String status;

    public void permit(){
        this.status = "permitted";
    }

    public void reject(){
        this.status = "rejected";
    }
}
