package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CompetitionRepository;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TeamRepository repository;

    @Autowired
    CompetitionRepository competitionRepository;

    @Override
    public Team createTeam(Team team) {
        team.getMembers().add(team.getCreatedUser());
        return repository.save(team);
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

    @Override
    public Team remove(String name) {
        return null;
    }
}
