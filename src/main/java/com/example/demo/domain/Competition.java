package com.example.demo.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Competition {
    @Id
    private String _id;
    private String name;
    private List<String> category = new ArrayList<>();
    private String group;
    private Date startDate;
    private Date endDate;

    public void addCategory(String category){
        this.category.add(category);
    }
}
