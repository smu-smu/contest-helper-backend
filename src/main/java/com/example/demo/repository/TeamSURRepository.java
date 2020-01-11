package com.example.demo.repository;

import com.example.demo.domain.TeamSignUpRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamSURRepository extends MongoRepository<TeamSignUpRequest, String> {

    List<TeamSignUpRequest> findByAccountId(String accountId);
    List<TeamSignUpRequest> findByTeamId(String teamId);
    TeamSignUpRequest findByTeamIdAndAccountId(String teamId, String accountId);

}
