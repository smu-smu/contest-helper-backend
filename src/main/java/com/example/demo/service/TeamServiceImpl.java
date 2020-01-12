package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Estimate;
import com.example.demo.domain.Message;
import com.example.demo.domain.Participant;
import com.example.demo.domain.TagScore;
import com.example.demo.domain.Team;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CompetitionRepository;
import com.example.demo.repository.EstimateRepository;
import com.example.demo.repository.TeamRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  TeamRepository repository;

  @Autowired
  CompetitionRepository competitionRepository;

  @Autowired
  EstimateRepository estimateRepository;

  @Override
  public Team createTeam(Team team) {
    team.getMembers().add(team.getCreatedUser());
    team.setState("inAction");
    return repository.save(team);
  }

  @Override
  public Team closeTeam(Team team) {
    Team tempTeam = repository.findById(team.getName()).get();
    if (!(tempTeam.getState().equals("Closed") || tempTeam.getState().equals("terminated"))) {
      return null;
    }
    for (String memberId : tempTeam.getMembers()) {
      Estimate estimate = new Estimate();
      estimate.setAccountId(memberId);
      estimate.setTeamId(tempTeam.getName());
      estimateRepository.save(estimate);
      Message message = new Message();
      message.setTitle("평가를 해주세요!! - " + tempTeam.getName());
      message.setContent(tempTeam.getName() + "팀의 활동이 종료되었습니다.");
      Account tempAccount = accountRepository.findById(memberId).get();
      tempAccount.getMessages().add(message);
      accountRepository.save(tempAccount);
    }
    tempTeam.setState("Closed");
    return repository.save(tempTeam);
  }

  @Override
  public Team terminateTeam(Team team) {
    Team tempTeam = repository.findById(team.getName()).get();
    if (!estimateRepository.findByTeamId(tempTeam.getName()).isEmpty()) {
      return null;
    }
    team.setState("Terminated");
    return repository.save(tempTeam);
  }

  @Override
  public Participant getParticipantById(String teamId, String id) {
    Team team = repository.findById(teamId).get();
    List<Participant> participants = team.getParticipants();
    for (Participant participant : participants) {
      if (participant.getAccountId().equals(id)) {
        return participant;
      }
    }
    return new Participant();
  }

  @Override
  public List<Team> getTeams() {
    return repository.findAll();
  }

  @Override
  public Team request(Participant participant) {
    Team team = repository.findById(participant.getTeamId()).get();
    team.getParticipants().add(participant);
    return repository.save(team);
  }

  @Override
  public List<Participant> getParticipants(String teamId) {
    Team team = repository.findById(teamId).get();
    return team.getParticipants();
  }

  public Integer getParticipantIndex(List<Participant> participants, String accountId) {
    int index = -1;
    for (Participant participant : participants) {
      index++;
      if (participant.getAccountId().equals(accountId)) {
        return index;
      }
    }
    return index;
  }

  @Override
  public Team reject(Participant participant) {
    Team team = repository.findById(participant.getTeamId()).get();
    List<Participant> participants = team.getParticipants();
    int index = getParticipantIndex(participants, participant.getAccountId());
    participants.remove(index);

    // user 알람
    Account account = accountRepository.findById(participant.getAccountId()).get();
    account.getMessages().add(new Message("reject", participant.getTeamId() + " reject you"));
    accountRepository.save(account);

    return repository.save(team);
  }

  @Override
  public Team permit(Participant participant) {
    Team team = repository.findById(participant.getTeamId()).get();
    List<Participant> participants = team.getParticipants();
    System.out.println(participants);
    int index = getParticipantIndex(participants, participant.getAccountId());
    participants.remove(index);

    System.out.println(participants);
    team.getMembers().add(participant.getAccountId());

    // user 알람
    Account account = accountRepository.findById(participant.getAccountId()).get();
    account.getMessages().add(new Message("permit", participant.getTeamId() + " permit you"));
    accountRepository.save(account);

    return repository.save(team);
  }

  @Override
  public Team updateComment(Team team) {
    Team team1 = repository.findById(team.getName()).get();
    team1.setComment(team.getComment());
    return repository.save(team1);
  }

  @Override
  public List<Team> getTeamsByContestId(String contestId) {
    return repository.findByContestId(contestId);
  }

  public Double getTeamScore(String teamId) {
    Team team = repository.findById(teamId).get();
    List<Account> members = new ArrayList<>();
    List<String> membersIds = team.getMembers();
    for (String id : membersIds) {
      members.add(accountRepository.findById(id).get());
    }
    List<String> tags = competitionRepository.findById(team.getContestId()).get().getCategory();

    int plusCount = 0;
    double sum = 0;

    for (String tag : tags) {
      for (Account account : members) {
        for (TagScore tagScore : account.getAvgTagScores()) {
          if (tagScore.getTagName().equals(tag)) {
            sum += tagScore.getScore();
            ++plusCount;
            break;
          }
        }
      }
    }
    double avg = sum / plusCount;

    return avg;
  }
}
