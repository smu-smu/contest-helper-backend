package com.example.demo.service;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamSignUpRequest;

import java.util.List;

public interface TeamSURService {

    List<TeamSignUpRequest> getSURInfoByAccount(String accountId);

    List<TeamSignUpRequest> getSURInfoByTeam(String teamId);

    Team permitSignUp(String teamId, String accountId);
    void rejectSignUp(String teamId, String accountId);

}
