package smu.team.study1.Service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsoupService {

    public void getDocument(String url) throws IOException {
        // 1. select() 사용법
        //      클래스 이름으로 가져오기
        //      doc.select(".클래스이름");
        //      id로 가져오기
        //      doc.select("#id");
        //      클래스에서 <a>태그만 파싱하기
        //      doc.select(".클래스이름 a");
        Document doc = Jsoup.connect(url).get();
        System.out.println("DOCUMENT===============");
        System.out.println(doc.toString());
    }

    public String getDocumentAsString(String url) throws IOException{
        return Jsoup.connect(url).get().toString();
    }


    public JSONObject getDocumentAsJson(String url) throws IOException, ParseException {
        String doc = Jsoup.connect(url).get().toString();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(doc);
        return obj;
    }

    public Document getDocumentAsDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }

}
