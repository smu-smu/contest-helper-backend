//package com.example.demo.repository;
//
//import com.example.demo.domain.Competition;
//import com.example.demo.service.JsoupService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CompetitionRepositoryTest {
//    @Autowired
//    JsoupService jsoupService;
//
//    @Autowired
//    CompetitionRepository repo;
//
//    @Test
//    public void 공모전추가() throws ParseException {
//        Competition comp = new Competition();
//        comp.setName("테스트공모전");
//        comp.setGroup("상명대");
//        comp.addCategory("아이디어");
//        comp.addCategory("웹");
//        comp.addCategory("프로그래밍");
//        comp.addCategory("게임");
//        String start = "2020.01.09";
//        String end = "2020.01.15";
//        SimpleDateFormat dt = new SimpleDateFormat("yyyy.MM.dd");
//        Date startDate = dt.parse(start);
//        Date endDate = dt.parse(end);
//        comp.setStartDate(startDate);
//        comp.setEndDate(endDate);
//
//
//        repo.save(comp);
//
//        List<Competition> list = (List<Competition>) repo.findAll();
//
//        for (Competition c : list) {
//            System.out.println(c.toString());
//        }
//    }
//}