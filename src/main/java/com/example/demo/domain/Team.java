package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Team {

  @Id
  @NotNull
  private String name;
  private String contestId;
  private String comment;
  private String address;

  @NotNull
  private String createdUser;

  private String state;

  private List<String> members = new ArrayList<>();

  private List<Participant> participants = new ArrayList<>();
}
