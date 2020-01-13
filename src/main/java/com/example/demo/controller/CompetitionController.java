package com.example.demo.controller;

import com.example.demo.aspect.PerfLogging;
import com.example.demo.domain.Competition;
import com.example.demo.service.CompetitionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class CompetitionController {

  @Autowired
  CompetitionService competitionService;

  @GetMapping("/contest/deleteAll")
  public void contest_deleteAll() {
    competitionService.deleteAll();
  }

  /**
   * 데이테베이스에 있는 전체 공모전 목록 출력
   *
   * @return 전체 공모전 목록
   */
  @PerfLogging
  @GetMapping("/contest/list")
  public List<Competition> contest_list() {
    return competitionService.findAll();
  }
}
