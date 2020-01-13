package com.example.demo.repository;

import com.example.demo.domain.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetitionRepository extends MongoRepository<Competition, String> {

}
