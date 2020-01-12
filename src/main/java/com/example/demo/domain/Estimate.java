package com.example.demo.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Estimate {

  public Estimate(String teamId, String accountId, String personId, String contestId) {
    this.accountId = accountId;
    this.teamId = teamId;
    this.personId = personId;
    this.contestId = contestId;
  }

  @NotNull
  private String accountId;
  @NotNull
  private String personId;
  @NotNull
  private String teamId;
  @NotNull
  private String contestId;
  @Min(0)
  private Double score;
}
