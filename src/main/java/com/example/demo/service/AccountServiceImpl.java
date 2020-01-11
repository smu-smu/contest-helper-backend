package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CompetitionRepository;
import com.example.demo.repository.EstimateRepository;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;
    @Autowired
    EstimateRepository estimateRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    CompetitionRepository competitionRepository;


    @Override
    public Account getUserInfo(String userId) {
        return repository.findById(userId).orElseGet(() -> new Account());
    }

    @Override
    public Account signup(Account account) {
        return repository.save(account);
    }

    @Override
    public List<Account> getUsersByTag(String tag) {
        return repository.findByFavoritesContains(tag);
    }

    @Override
    public List<Account> getUsers() {
        return repository.findAll();
    }

    @Override
    public List<String> getUserProfile(String userId) {
        return getUserInfo(userId).getProfiles();
    }

    @Override
    public List<Account> getUsersByProfile(String profile) {
        return repository.findByProfilesContains(profile);
    }

    @Override
    public Account addTagsToUser(String userId, List<String> favorites) {
        Account account = repository.findById(userId).get();
        account.getFavorites().addAll(favorites);
        return repository.save(account);
    }

    @Override
    public Account addProfilesToUser(String userId, List<String> profiles) {
        Account account = repository.findById(userId).get();
        account.getProfiles().addAll(profiles);
        return repository.save(account);
    }

    @Override
    public List<Estimate> getEstiListByAccountId(String userId) {
        return estimateRepository.findByAccountId(userId);
    }

    @Override
    public List<Account> getAppraiseeByEstimate(Estimate estimate){
        List<Account> accounts = new ArrayList<>();
        Team tempTeam = teamRepository.findById(estimate.getTeamId()).get();
        for (String accountId : tempTeam.getMembers()) {
            if (accountId != estimate.getAccountId()) {
                accounts.add(repository.findById(accountId).get());
            }
        }
        return accounts;
    }

    @Override
    public Account estimateTeam(Estimate estimate, List<EstimateScore> scores) {
        Team tempTeam = teamRepository.findById(estimate.getTeamId()).get();
        Competition tempComp = competitionRepository.findById(tempTeam.getContestId()).get();
        for (String accountId : tempTeam.getMembers()) {
            Account account = repository.findById(accountId).get();
            if (!account.getUserId().equals(estimate.getAccountId())) {
                for (String category : tempComp.getCategory()) {
                    TagScore tagScore = new TagScore();
                    tagScore.setTagName(category);
                    for (EstimateScore estimateScore : scores) {
                        if (estimateScore.getAccountId().equals(accountId)) {
                            tagScore.setScore(estimateScore.getScore());
                        }
                    }
                    newTagScore(tagScore, accountId);
                }
            }
        }
        estimateRepository.delete(estimate);
        return repository.findById(estimate.getAccountId()).get();
    }

    @Override
    public Account newTagScore(TagScore newTags, String userId) {
        Account account = repository.findById(userId).get();

        account.getTagScores().add(newTags);

        List<TagScore> tagScores = account.getTagScores();
        List<TagScore> avgScores = account.getAvgTagScores();

        boolean contains = false;
        for (TagScore tag : avgScores) {
            if (tag.getTagName().equals(newTags.getTagName())) {
                contains = true;
                break;
            }
        }
        if (contains) {
            double sum = 0;
            int tagCount = 0;
            for (TagScore tagScore : tagScores) {
                if (tagScore.getTagName().equals(newTags.getTagName())) {
                    sum += tagScore.getScore();
                    ++tagCount;
                }
            }
            for (TagScore avgScore : avgScores) {
                if (avgScore.getTagName().equals(newTags.getTagName())) {
                    avgScore.setScore(sum / tagCount);
                }
            }
        } else {
            avgScores.add(newTags);
        }
        return repository.save(account);
    }

    @Override
    public Account sendMessage(Message message, String userId) {
        Account account = repository.findById(userId).get();
        account.getMessages().add(message);
        return repository.save(account);
    }

    @Override
    public Account deleteMessage(String userId, Integer messageId) {
        Account account = repository.findById(userId).get();
        if (!account.getMessages().isEmpty()) {
            account.getMessages().remove(messageId - 1);
        }
        return repository.save(account);
    }

    @Override
    public Account signin(Account account) {
        return repository.findByUserIdAndAndPassword(account.getUserId(), account.getPassword()).orElseGet(() -> new Account());
    }

    public List<String> getTeamsById(String userId) {
        return repository.findById(userId).get().getMyTeams();
    }


}
