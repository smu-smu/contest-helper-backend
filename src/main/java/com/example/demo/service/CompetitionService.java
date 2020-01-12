package com.example.demo.service;

import com.example.demo.domain.Competition;
import com.example.demo.repository.CompetitionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

  @Autowired
  CompetitionRepository repository;

  public List<Competition> findAll() {
    return (List<Competition>) repository.findAll();
  }

  public void deleteAll() {
    repository.deleteAll();
  }
}
