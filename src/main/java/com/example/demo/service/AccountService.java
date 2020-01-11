package com.example.demo.service;

import com.example.demo.domain.*;

import java.util.List;

public interface AccountService {
    Account getUserInfo(String id);

    Account signup(Account account);

    List<Account> getUsersByTag(String tag);

    List<Account> getUsers();

    List<String> getUserProfile(String userId);

    List<Account> getUsersByProfile(String profile);

    Account addTagsToUser(String userId, List<String> profiles);

    Account addProfilesToUser(String userId, List<String> profiles);

    List<Estimate> getEstiListByAccountId(String userId);

    Account newTagScore(TagScore tagScore, String userId);

    List<Account> getAppraiseeByEstimate(Estimate estimate);

    Account estimateTeam(Estimate estimate, List<EstimateScore> scores);

    Account sendMessage(Message message, String userId);

    Account deleteMessage(String userId, Integer messageId);

    Account signin(Account account);


    List<String> getTeamsById(String userId);
}
