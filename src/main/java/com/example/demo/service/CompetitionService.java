package com.example.demo.service;

import com.example.demo.domain.Competition;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface CompetitionService {

  void save(String name, ArrayList<String> category, String group, String start, String end)
      throws ParseException;

  List<Competition> findAll();

  void deleteAll();
}
