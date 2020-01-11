package com.example.demo.service;

import com.example.demo.domain.Competition;
import com.example.demo.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompetitionService {
    @Autowired
    CompetitionRepository competitionRepository;

    public void save(String name, ArrayList<String> category, String group, String start, String end) throws ParseException {
        Competition comp = new Competition();
        comp.setName(name);
        comp.setCategory(category);
        comp.setGroup(group);
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy.MM.dd");
        Date startDate = dateForm.parse(start);
        Date endDate = dateForm.parse(end);
        comp.setStartDate(start);
        comp.setEndDate(end);

        comp.set_id(name+group+start);

        competitionRepository.save(comp);
    }

    public List<Competition> findAll(){
        return (List<Competition>) competitionRepository.findAll();
    }

    public void deleteAll(){
        competitionRepository.deleteAll();
    }

}
