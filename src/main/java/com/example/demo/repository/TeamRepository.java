package com.example.demo.repository;

import com.example.demo.domain.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, String> {

    List<Team> findByContestId(String contestId);
}
