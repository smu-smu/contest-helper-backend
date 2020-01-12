package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Participant;
import com.example.demo.domain.Team;
import java.util.List;

public interface TeamService {

  Team createTeam(Team team);

  List<Team> getTeams();

  Team request(Participant participant);

  List<Participant> getParticipants(String teamId);

  Team reject(Participant participant);

  Team permit(Participant participant);

  Team updateComment(Team team);

  List<Team> getTeamsByContestId(String contestId);

  String deleteTeam(String teamId);
}
