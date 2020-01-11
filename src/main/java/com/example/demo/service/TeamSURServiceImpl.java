package com.example.demo.service;

import com.example.demo.domain.TeamSignUpRequest;
import com.example.demo.repository.TeamSURRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamSURServiceImpl implements TeamSURService {

    @Autowired
    TeamSURRepository teamSURRepository;

    @Override
    public List<TeamSignUpRequest> getSURInfoByAccount(String accountId) {
        return teamSURRepository.findByAccountId(accountId);
    }

    @Override
    public List<TeamSignUpRequest> getSURInfoByTeam(String teamId) {
        return teamSURRepository.findByTeamId(teamId);
    }
}
