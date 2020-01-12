package com.example.demo.runner;

import com.example.demo.aspect.PerfLogging;
import com.example.demo.domain.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountInit implements ApplicationRunner {

  @Autowired
  AccountRepository repository;

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

    repository.save(account1);
    repository.save(account2);
    repository.save(account3);
  }
}