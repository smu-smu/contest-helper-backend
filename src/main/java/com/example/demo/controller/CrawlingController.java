package com.example.demo.controller;

import com.example.demo.domain.Competition;
import com.example.demo.service.CompetitionService;
import com.example.demo.service.JsoupService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CrawlingController {

  private static String URL = "https://www.thinkcontest.com/";

  @Autowired
  JsoupService jsoupService;

  @Autowired
  CompetitionService competitionService;

  @GetMapping("/contest")
  public void contest() throws IOException, ParseException {
    Document doc = jsoupService.getDocument(URL);
    Elements contest = doc.select(".all-contest");
    Elements tbody = contest.select("tbody");
    for (Element tr : tbody.select("tr")) {
      Elements tds = tr.select("td");
      String contestName = tds.get(0).selectFirst(".contest-title").text();
      Elements contestCate = tds.get(0).select(".contest-cate");
      ArrayList<String> category = new ArrayList<>();
      for (Element e : contestCate.select(".cate-name")) {
        String item = e.text();
        category.add(item);
      }
      String contestGroup = tds.get(1).text();
      String contestDates = tds.get(3).text();
      String startDate = contestDates.substring(0, 10);
      String endDate = contestDates.substring(13, 23);

      competitionService.save(contestName, category, contestGroup, startDate, endDate);
    }
  }

  @GetMapping("/contest/deleteAll")
  public void contest_deleteAll() {
    competitionService.deleteAll();
  }

  @GetMapping("/contest/list")
  public List<Competition> contest_list() {
    return competitionService.findAll();
  }
}
