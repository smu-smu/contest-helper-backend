package com.example.demo.runner;

import com.example.demo.aspect.PerfLogging;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.CompetitionService;
import com.example.demo.service.JsoupService;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AcconutInit implements ApplicationRunner {

  private final static String URL = "https://www.thinkcontest.com/";
  @Autowired
  AccountRepository repository;

  @Autowired
  JsoupService jsoupService;

  @Autowired
  CompetitionService competitionService;

  @PerfLogging
  @Override
  public void run(ApplicationArguments args) throws Exception {
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
//        repository.deleteAll();
  }
}
