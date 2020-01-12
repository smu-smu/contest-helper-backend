package com.example.demo.repository;

import com.example.demo.domain.Page;
import java.io.IOException;
import java.util.Optional;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageRepositoryTest {

  private final String URL = "https://www.thinkcontest.com/";

  @Autowired
  PageRepository repo;


  @Test
  public void 페이지추가() throws IOException {
    String doc = Jsoup.connect(URL).get().toString();
    Page page = new Page();
    page.setText(doc);
    page.setId("now");

    repo.save(page);

    Optional<Page> byId = repo.findById("now");
    System.out.println(byId.get());
  }
}