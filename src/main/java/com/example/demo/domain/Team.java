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



    private String comment;
    //private String compid;

    private String contactAddress;
    private List<String> teammatesIds = new ArrayList<>();

    public void addTeammate(String accountId) {
        this.teammatesIds.add(accountId);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
}
