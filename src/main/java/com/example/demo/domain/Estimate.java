package com.example.demo.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class Estimate {

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

  public Estimate(String teamId, String accountId, String personId, String contestId) {
    this.accountId = accountId;
    this.teamId = teamId;
    this.personId = personId;
    this.contestId = contestId;
  }
}
