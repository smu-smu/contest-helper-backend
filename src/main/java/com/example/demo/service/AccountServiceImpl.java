package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Message;
import com.example.demo.domain.TagScore;
import com.example.demo.repository.AccountRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository repository;

  public AccountServiceImpl(AccountRepository repository) {
    this.repository = repository;
  }

  @Override
  public Account getUserInfo(String userId) {
    return repository.findById(userId).orElseGet(Account::new);
  }

  @Override
  public Account signup(Account account) {
    if (repository.findById(account.getUserId()).isPresent()) {
      log.info("해당 id 이미 존재");
      return new Account();
    } else {
      log.info("가입 완료");
      return repository.save(account);
    }
  }

  @Override
  public List<Account> getUsersByTag(String tag) {
    return repository.findByTagsContains(tag);
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
    Account account = repository.findById(userId).orElseGet(Account::new);
    account.getTags().addAll(favorites);
    return repository.save(account);
  }

  @Override
  public Account addProfilesToUser(String userId, List<String> profiles) {
    Account account = repository.findById(userId).orElseGet(Account::new);
    account.getProfiles().addAll(profiles);
    return repository.save(account);
  }

  @Override
  public Account sendMessage(Account acc) {
    Account account = repository.findById(acc.getUserId()).orElseGet(Account::new);
    account.getMessages().addAll(acc.getMessages());
    return repository.save(account);
  }

  @Override
  public Account addTagScore(String userId, TagScore tagScore) {
    Optional<Account> byId = repository.findById(userId);
    if (byId.isPresent()) {
      byId.get().getTagScores().add(tagScore);
      return repository.save(byId.get());
    } else {
      return new Account();
    }
  }

  @Override
  public String deleteUser(String userId) {
    try {
      repository.deleteById(userId);
    } catch (Exception e) {
      return "fail";
    }
    return "success";
  }

  @Override
  public Account deleteMessage(String userId, Integer messageId) {
    Account account = repository.findById(userId).orElseGet(Account::new);
    if (!account.getMessages().isEmpty()) {
      account.getMessages().remove(messageId - 1);
    }
    return repository.save(account);
  }

  @Override
  public Account signin(Account account) {
    return repository.findByUserIdAndAndPassword(account.getUserId(), account.getPassword())
        .orElseGet(Account::new);
  }

  public List<String> getTeamsById(String userId) {
    return repository.findById(userId).orElseGet(Account::new).getTeam();
  }

  @Override
  public List<Message> getUserMessages(String userId) {
    return repository.findById(userId).orElseGet(Account::new).getMessages();
  }

  @Override
  public List<String> getUserTags(String userId) {
    Account account = repository.findById(userId).orElseGet(Account::new);
    return account.getTags();
  }

  @Override
  public List<String> getUserTeam(String userId) {
    Account account = repository.findById(userId).orElseGet(Account::new);
    return account.getTeam();
  }

  @Override
  public List<TagScore> getUserTagScores(String userId) {
    return repository.getUserTagScores(userId);
  }

  @Override
  public Double getUserTagScore(String userId, String tagName) {
    List<TagScore> results = repository.getUserTagScore(userId, tagName);
    for (TagScore tag : results) {
      if (tag.get_id().equals(tagName)) {
        return tag.getScore();
      }
    }
    return -1.0;
  }
}
