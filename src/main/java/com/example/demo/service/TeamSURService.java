package com.example.demo.service;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamSignUpRequest;

import java.util.List;

public interface TeamSURService {

    List<TeamSignUpRequest> getSURInfoByAccount(String accountId);

    List<TeamSignUpRequest> getSURInfoByTeam(String teamId);

    TeamSignUpRequest requestSignUp(String teamId, String accountId);
    TeamSignUpRequest permitSignUp(String teamId, String accountId);
    TeamSignUpRequest rejectSignUp(String teamId, String accountId);

}
