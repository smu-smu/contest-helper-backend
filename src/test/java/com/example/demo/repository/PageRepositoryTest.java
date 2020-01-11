package com.example.demo.repository;

import com.example.demo.domain.Page;
import com.example.demo.service.JsoupService;
import java.io.IOException;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageRepositoryTest {

  @Autowired
  PageRepository repo;

  @Autowired
  JsoupService jsoup;

  @Test
  public void 페이지추가() throws IOException {
    String doc = jsoup.getDocumentAsString("https://www.thinkcontest.com/");
    Page page = new Page();
    page.setText(doc);
    page.setId("now");

    repo.save(page);

    Optional<Page> byId = repo.findById("now");
    System.out.println(byId.get());
  }
}