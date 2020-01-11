package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Team;

import java.util.List;

public interface TeamService {
    Team getTeamInfo(String id);
    Team createTeam(Team team);
    List<Team> getTeams();
    //List<Team> getTeamsByCmpt(Competition competition);
    List<Team> getTeamsByAccount(String accountId);

}
