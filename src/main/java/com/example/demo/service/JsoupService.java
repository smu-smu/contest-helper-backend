package com.example.demo.service;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JsoupService {

  public Document getDocument(String url) throws IOException {
    // 1. select() 사용법
    //      클래스 이름으로 가져오기
    //      doc.select(".클래스이름");
    //      id로 가져오기
    //      doc.select("#id");
    //      클래스에서 <a>태그만 파싱하기
    //      doc.select(".클래스이름 a");
    return Jsoup.connect(url).get();
  }

  public String getDocumentAsString(String url) throws IOException {
    return Jsoup.connect(url).get().toString();
  }

  public Elements getElementsByClassName(String url, String className) throws IOException {
    Document doc = Jsoup.connect(url).get();
    Elements elements = doc.select(className);
    return elements;
  }
}
