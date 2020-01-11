package com.example.demo.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Estimate {

  @NotNull
  private String accountId;

  @NotNull
  private String teamId;

  @NotNull
  private String personId;

  @Min(0)
  private Double score = 0.0;
}
