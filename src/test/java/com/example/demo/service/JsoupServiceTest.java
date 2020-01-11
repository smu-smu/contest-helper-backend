package com.example.demo.service;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsoupServiceTest {

  @Autowired
  JsoupService service;

  @Test
  public void 테이블크롤링() throws IOException {
    String url = "https://www.thinkcontest.com/";
    Document doc = service.getDocument(url);
    Elements contest = service.getElementsByClassName(url, ".all-contest");
    Elements tbody = contest.select("tbody");
    for (Element tr : tbody.select("tr")) {
      Elements tds = tr.select("td");
      String contestName = tds.get(0).selectFirst(".contest-title").text();
      Elements contestCate = tds.get(0).select(".contest-cate");

      System.out.println(contestCate.text());
      System.out.println("------");
    }
  }
}