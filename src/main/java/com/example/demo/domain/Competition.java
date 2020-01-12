package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Competition {

  @Id
  private String id;
  private String name;
  private List<String> category = new ArrayList<>();
  private String group;
  private Date startDate;
  private Date endDate;


//  public Competition(Long count, String name, List<String> category, String group, Date startDate,
//      Date endDate) {
//    this.id= String.valueOf(count);
//    this.name = name;
//    this.category = category;
//    this.group = group;
//    this.startDate = startDate;
//    this.endDate = endDate;
//  }
}
