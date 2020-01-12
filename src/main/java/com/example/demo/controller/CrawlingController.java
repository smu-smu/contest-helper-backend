package com.example.demo.controller;

import com.example.demo.aspect.PerfLogging;
import com.example.demo.domain.Competition;
import com.example.demo.service.CompetitionService;
import com.example.demo.service.JsoupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class CrawlingController {

  private static String URL = "https://www.thinkcontest.com/";

  @Autowired
  JsoupService jsoupService;

  @Autowired
  CompetitionService competitionService;

  @GetMapping("/contest/deleteAll")
  public void contest_deleteAll() {
    competitionService.deleteAll();
  }

  @PerfLogging
  @GetMapping("/contest/list")
  public List<Competition> contest_list() {
    return competitionService.findAll();
  }
}
