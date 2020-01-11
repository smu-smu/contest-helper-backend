package com.example.demo.domain;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Participant {

  @Id
  @NotNull
  private String accountId;
  @NotNull
  private String teamId;
  private String status = "waiting";
}
