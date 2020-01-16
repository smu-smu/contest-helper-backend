package com.example.demo.runner;

import com.example.demo.aspect.PerfLogging;
import com.example.demo.domain.Account;
import com.example.demo.domain.TagScore;
import com.example.demo.repository.AccountRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountInit implements ApplicationRunner {

  private final AccountRepository repository;

  public AccountInit(AccountRepository repository) {
    this.repository = repository;
  }

  @PerfLogging
  @Override
  public void run(ApplicationArguments args) throws Exception {
    repository.deleteAll();
    Account account1 = new Account();
    account1.setUserId("user1");
    account1.setPassword("password");
    account1.setName("user1");

    Account account2 = new Account();
    account2.setUserId("user2");
    account2.setPassword("password");
    account2.setName("user2");

    Account account3 = new Account();
    account3.setUserId("user3");
    account3.setPassword("password");
    account3.setName("user3");

    TagScore tagScore1 = new TagScore("AI", 5.0);
    TagScore tagScore2 = new TagScore("AI", 3.0);
    account3.getTagScores().add(tagScore1);
    account3.getTagScores().add(tagScore2);

    repository.save(account1);
    repository.save(account2);
    repository.save(account3);
  }
}
