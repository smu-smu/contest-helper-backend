package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Message;
import com.example.demo.domain.TagScore;

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

    Account updateTagScores(TagScore tagScore, String userId);

    Account sendMessage(Message message, String userId);

    Account deleteMessage(String userId, Integer messageId);
}
