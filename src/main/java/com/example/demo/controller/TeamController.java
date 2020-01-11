package com.example.demo.controller;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamSignUpRequest;
import com.example.demo.service.TeamSURService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/teamSUR/account/{accountId}")
    public List<TeamSignUpRequest> teamSURListByAccountId(@PathVariable String accountId) {
        return teamSURService.getSURInfoByAccount(accountId);
    }

    @GetMapping("/teamSUR/team/{teamId}")
    public List<TeamSignUpRequest> teamSURListByTeamId(@PathVariable String teamId) {
        return teamSURService.getSURInfoByTeam(teamId);
    }


    @GetMapping("/team/account/{accountId}")
    public List<Team> teamListByAccountId(@PathVariable String accountId){
        return null;
        //return teamService.getTeamsByAccount()
    }

    /** POST **/

    @PostMapping("/team/create")
    public Team createTeam(@RequestBody Team team) {
        System.out.println(team);
        return teamService.createTeam(team);
    }

    @PostMapping("/team/permit")
    public Team createTeam(@RequestParam String teamId, @RequestParam String accountId){
        System.out.println(teamId + "permit" + accountId);
        return teamSURService.permitSingUp(teamId, accountId);
    }



}
