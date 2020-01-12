package com.example.demo.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Estimate {

  public Estimate(String teamId, String accountId, String tag, String personId) {
    this.accountId = accountId;
    this.teamId = teamId;
    this.tag = tag;
    this.personId = personId;
  }

  @NotNull
  private String accountId;
  @NotNull
  private String personId;
  @NotNull
  private String teamId;
  @NotNull
  private String tag;
  @Min(0)
  private Double score;
}
