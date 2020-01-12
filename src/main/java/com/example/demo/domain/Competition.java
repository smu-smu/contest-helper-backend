package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class Competition {

  @Id
  private String id;
  private String name;
  private List<String> category = new ArrayList<>();
  private String group;
  private Date startDate;
  private Date endDate;
}
