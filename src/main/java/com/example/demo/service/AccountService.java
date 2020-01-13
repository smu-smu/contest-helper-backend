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

  Account deleteMessage(String userId, Integer messageId);

  Account signin(Account account);

  List<String> getTeamsById(String userId);

  List<Message> getUserMessages(String userId);

  List<String> getUserTags(String userId);

  List<String> getUserTeam(String userId);

  List<TagScore> getUserTagScores(String userId);

  Double getUserTagScore(String userId, String tag);

  Account sendMessage(Account account);

  Account addTagScore(String userId, TagScore tagScore);

  String deleteUser(String userId);
}
