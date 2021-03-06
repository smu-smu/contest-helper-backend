package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Competition;
import com.example.demo.domain.Estimate;
import com.example.demo.domain.Message;
import com.example.demo.domain.Participant;
import com.example.demo.domain.Team;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CompetitionRepository;
import com.example.demo.repository.EstimateRepository;
import com.example.demo.repository.TeamRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

  private final AccountRepository accountRepository;

  private final TeamRepository repository;

  private final CompetitionRepository competitionRepository;

  private final EstimateRepository estimateRepository;

  public TeamServiceImpl(AccountRepository accountRepository,
      TeamRepository repository,
      CompetitionRepository competitionRepository,
      EstimateRepository estimateRepository) {
    this.accountRepository = accountRepository;
    this.repository = repository;
    this.competitionRepository = competitionRepository;
    this.estimateRepository = estimateRepository;
  }

  @Override
  public Team createTeam(Team team) {
    team.getMembers().add(team.getCreatedUser());

    Optional<Account> byId = accountRepository.findById(team.getCreatedUser());
    if (byId.isPresent()) {
      Account account = byId.get();
      account.getTeam().add(team.getName());
      accountRepository.save(account);
    }

    return repository.save(team);
  }

  @Override
  public List<Team> getTeams() {
    return repository.findAll();
  }

  @Override
  public Team request(Participant participant) {
    Team team = repository.findById(participant.getTeamId()).orElseGet(Team::new);
    if (team.equals(new Team())) {
      return team;
    } else {
      team.getParticipants().add(participant);
      return repository.save(team);
    }
  }

  @Override
  public List<Participant> getParticipants(String teamId) {
    Optional<Team> teamTmp = repository.findById(teamId);
    if (teamTmp.isPresent()) {
      Team team = teamTmp.get();
      return team.getParticipants();
    } else {
      return new ArrayList<>();
    }
  }

  public Integer getParticipantIndex(List<Participant> participants, String accountId) {
    int index = -1;
    boolean check = false;
    for (Participant participant : participants) {
      index++;
      if (participant.getAccountId().equals(accountId)) {
        check = true;
        break;
      }
    }
    return check ? index : -1;
  }

  @Override
  public Team reject(Participant participant) {
    Optional<Team> teamTmp = repository.findById(participant.getTeamId());
    if (teamTmp.isPresent()) {
      Team team = teamTmp.get();
      List<Participant> participants = team.getParticipants();
      int index = getParticipantIndex(participants, participant.getAccountId());
      if (index >= 0) {
        participants.remove(index);
        // user 알람
        Account account = accountRepository.findById(participant.getAccountId()).get();
        int messageCnt = account.getMessages().size();
        account.getMessages().add(new Message(String.valueOf(messageCnt++), "reject",
            participant.getTeamId() + " reject you"));
        accountRepository.save(account);
        return repository.save(team);
      } else {
        return team;
      }
    } else {
      return new Team();
    }
  }

  @Override
  public Team permit(Participant participant) {
    Optional<Team> teamTmp = repository.findById(participant.getTeamId());
    if (teamTmp.isPresent()) {
      Team team = teamTmp.get();
      List<Participant> participants = team.getParticipants();
      int index = getParticipantIndex(participants, participant.getAccountId());
      participants.remove(index);
      team.getMembers().add(participant.getAccountId());

      // user 알람
      Account account = accountRepository.findById(participant.getAccountId()).get();
      int messageCnt = account.getMessages().size();
      account.getMessages().add(new Message(String.valueOf(messageCnt++), "permit",
          participant.getTeamId() + " permit you"));
      account.getTeam().add(team.getName());
      accountRepository.save(account);
      return repository.save(team);
    } else {
      return new Team();
    }
  }

  @Override
  public Team updateComment(Team comment) {
    Optional<Team> teamTmp = repository.findById(comment.getName());
    if (teamTmp.isPresent()) {
      Team team = teamTmp.get();
      team.setComment(team.getComment());
      return repository.save(team);
    } else {
      return new Team();
    }
  }

  @Override
  public List<Team> getTeamsByContestId(String contestId) {
    return repository.findByContestId(contestId);
  }

  @Override
  public String deleteTeam(String teamId) {
    Optional<Team> byId = repository.findById(teamId);
    if (byId.isPresent()) {
      Team team = byId.get();

      System.out.println(team.getContestId());
      Optional<Competition> byId1 = competitionRepository.findById(team.getContestId());
      if (byId1.isPresent()) {
        // 평가 collection에 user 목록 추가
        List<String> members = team.getMembers();
        members.forEach(member -> {
          Optional<Account> byId2 = accountRepository.findById(member);
          if (byId2.isPresent()) {
            Account account = byId2.get();
            account.getTeam().remove(teamId);
            accountRepository.save(account);
          }
          members.forEach(m -> {
            if (!m.equals(member)) {
              estimateRepository.save(new Estimate(team.getName(), member, m, team.getContestId()));
            }
          });
        });
        repository.delete(team);
        return "success";
      } else {
        return "fail";
      }
    } else {
      return "fail";
    }
  }
}
