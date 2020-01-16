package com.example.demo.service;

import com.example.demo.domain.Competition;
import com.example.demo.repository.CompetitionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

  private final CompetitionRepository repository;

  public CompetitionService(CompetitionRepository repository) {
    this.repository = repository;
  }

  public List<Competition> findAll() {
    return (List<Competition>) repository.findAll();
  }

  public void deleteAll() {
    repository.deleteAll();
  }
}
