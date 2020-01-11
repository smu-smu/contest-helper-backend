package com.example.demo.controller;

import com.example.demo.domain.Team;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("team/create")
    public Team test(){
        return null;
    }



}
