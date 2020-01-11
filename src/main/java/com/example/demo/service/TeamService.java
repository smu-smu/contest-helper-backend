package com.example.demo.service;

import com.example.demo.domain.Team;
import com.example.demo.domain.Participant;

import java.util.List;

public interface TeamService {
    Team createTeam(Team team);

    Team closeTeam(Team team);

    Team terminateTeam(Team team);

    Participant getParticipantById(String teamId, String id);

    List<Team> getTeams();

    Team request(Participant participant);

    List<Participant> getParticipants(String teamId);

    Team reject(Participant participant);

    Team permit(Participant participant);

    Team updateComment(Team team);

    List<Team> getTeamsByContestId(String contestId);

    Double getTeamScore(String teamId);

}
