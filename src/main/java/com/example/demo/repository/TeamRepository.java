package com.example.demo.repository;

import com.example.demo.domain.Team;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {

  List<Team> findByContestId(String contestId);
}
