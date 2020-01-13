package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class Account {

  @Id
  @NotNull
  private String userId;
  private String password;
  private String name;

  private List<String> tags = new ArrayList<>();
  private List<String> profiles = new ArrayList<>();
  private List<String> team = new ArrayList<>();


  @Field("tagScores")
  private List<TagScore> tagScores = new ArrayList<>();

  @Field("messages")
  private List<Message> messages = new ArrayList<>();
}
