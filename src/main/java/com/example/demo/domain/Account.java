package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Account {
    @Id
    private String userId;
    private String password;
    private String name;

    private List<String> favorites = new ArrayList<>();
    private List<String> profiles = new ArrayList<>();

    public void addProfile(String profile){
        this.profiles.add(profile);
    }
    public void addFavorites(String favorite){
        this.favorites.add(favorite);
    }
}
