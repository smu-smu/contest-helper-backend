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

    @Override
    public TeamSignUpRequest requestSignUp(String teamId, String accountId) {
        TeamSignUpRequest nTSUR = new TeamSignUpRequest();
        nTSUR.setAccountId(accountId);
        nTSUR.setTeamId(teamId);
        nTSUR.setStatus("ing");
        return teamSURRepository.save(nTSUR);
    }


    //팀 가입 승인(teamId, accountId 사용)
    @Override
    public TeamSignUpRequest permitSignUp(String teamId, String accountId) {
        Team team = teamRepository.findById(teamId).get();
        team.addTeammate(accountId);
        TeamSignUpRequest tSUR = teamSURRepository.findByTeamIdAndAccountId(teamId, accountId);
        tSUR.permit();
        teamRepository.save(team);
        return teamSURRepository.save(tSUR);
    }

    //팀 가입 거절(teamId, accountId 사용)
    @Override
    public TeamSignUpRequest rejectSignUp(String teamId, String accountId) {
        TeamSignUpRequest tSUR = teamSURRepository.findByTeamIdAndAccountId(teamId, accountId);
        tSUR.reject();
        return teamSURRepository.save(tSUR);
    }

}
