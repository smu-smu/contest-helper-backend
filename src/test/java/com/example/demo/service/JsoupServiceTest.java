package com.example.demo.service;

import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsoupServiceTest {
    @Autowired
    JsoupService service;

    @Test
    public void 테이블크롤링() throws IOException {
        String url = "https://www.thinkcontest.com";
        String name = ".type-2.mg-t-5.contest-table";
        Elements table = service.getTableByClass(url,name);
        System.out.println(table);
    }
}