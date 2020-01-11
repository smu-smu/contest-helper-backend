package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Team;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Team getTeamInfo(String id) {
        return teamRepository.findById(id).get();
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> getTeamsByAccount(String accountId) {
        return teamRepository.findByTeammatesContains(accountId);
    }
}
