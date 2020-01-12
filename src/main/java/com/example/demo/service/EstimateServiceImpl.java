package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Estimate;
import com.example.demo.domain.TagScore;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.EstimateRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstimateServiceImpl implements EstimateService {

  @Autowired
  EstimateRepository repository;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  AccountService accountService;

  @Override
  public String delete(Estimate info) {
    repository.delete(info);
    Optional<Account> byId = accountRepository.findById(info.getAccountId());
    if (byId.isPresent()) {
      Account account = accountService
          .addTagScore(info.getAccountId(), new TagScore(info.getTag(), info.getScore()));
      accountRepository.save(account);
      return "success";
    } else {
      return "fail";
    }
  }
}
