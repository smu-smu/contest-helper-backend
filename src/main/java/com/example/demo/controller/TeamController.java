package com.example.demo.controller;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamSignUpRequest;
import com.example.demo.service.TeamSURService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamSURService teamSURService;

    @Autowired
    TeamService teamService;

    @GetMapping("team/create")
    public Team test() {
        return null;
    }

    @GetMapping("teamSUR/account/{accountId}")
    public List<TeamSignUpRequest> teamSURListByAccountId(@PathVariable String accountId) {
        return teamSURService.getSURInfoByAccount(accountId);
    }

    @GetMapping("teamSUR/team/{teamId}")
    public List<TeamSignUpRequest> teamSURListByTeamId(@PathVariable String teamId) {
        return teamSURService.getSURInfoByTeam(teamId);
    }

}
