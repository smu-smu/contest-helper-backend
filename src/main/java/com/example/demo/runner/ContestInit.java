package com.example.demo.runner;

import com.example.demo.aspect.PerfLogging;
import com.example.demo.domain.Competition;
import com.example.demo.repository.CompetitionRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class ContestInit implements ApplicationRunner {

  private final static String URL = "https://www.thinkcontest.com/";

  @Autowired
  CompetitionRepository repository;

  @PerfLogging
  @Override
  public void run(ApplicationArguments args) throws Exception {
    Document doc = Jsoup.connect(URL).get();
    Elements contest = doc.select(".all-contest");
    Elements tbody = contest.select("tbody");

    int index = 1;
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

      SimpleDateFormat transFormat = new SimpleDateFormat("yyyy.MM.dd");
      Date startDate = transFormat.parse(contestDates.substring(0, 10));
      Date endDate = transFormat.parse(contestDates.substring(13, 23));
      repository
          .save(new Competition(String.valueOf(index++), contestName, category, contestGroup,
              startDate, endDate));
    }
  }
}