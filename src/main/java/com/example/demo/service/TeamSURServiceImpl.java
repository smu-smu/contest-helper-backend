package com.example.demo.service;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamSignUpRequest;
import com.example.demo.repository.TeamRepository;
import com.example.demo.repository.TeamSURRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamSURServiceImpl implements TeamSURService {

    @Autowired
    TeamSURRepository teamSURRepository;
    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<TeamSignUpRequest> getSURInfoByAccount(String accountId) {
        return teamSURRepository.findByAccountId(accountId);
    }

    @Override
    public List<TeamSignUpRequest> getSURInfoByTeam(String teamId) {
        return teamSURRepository.findByTeamId(teamId);
    }


    //팀 가입 승인(teamId, accountId 사용)
    @Override
    public Team permitSignUp(String teamId, String accountId) {
        Team team = teamRepository.findById(teamId).get();
        team.addTeammate(accountId);
        TeamSignUpRequest tSUR = teamSURRepository.findByTeamIdAndAccountId(teamId, accountId);
        tSUR.permit();
        teamSURRepository.save(tSUR);
        return teamRepository.save(team);
    }

    //팀 가입 거절(teamId, accountId 사용)
    @Override
    public void rejectSignUp(String teamId, String accountId) {
        TeamSignUpRequest tSUR = teamSURRepository.findByTeamIdAndAccountId(teamId, accountId);
        tSUR.reject();
        teamSURRepository.save(tSUR);
    }

}
