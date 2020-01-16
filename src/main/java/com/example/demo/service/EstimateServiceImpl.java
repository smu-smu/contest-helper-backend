package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Competition;
import com.example.demo.domain.Estimate;
import com.example.demo.domain.TagScore;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CompetitionRepository;
import com.example.demo.repository.EstimateRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EstimateServiceImpl implements EstimateService {

  private final EstimateRepository repository;

  private final AccountRepository accountRepository;

  private final AccountService accountService;

  private final CompetitionRepository competitionRepository;

  public EstimateServiceImpl(EstimateRepository repository,
      AccountRepository accountRepository, AccountService accountService,
      CompetitionRepository competitionRepository) {
    this.repository = repository;
    this.accountRepository = accountRepository;
    this.accountService = accountService;
    this.competitionRepository = competitionRepository;
  }

  @Override
  public String delete(Estimate info) {
    repository.deleteEstimateByAccountIdAndPersonIdAndTeamIdAndContestId(
        info.getAccountId(), info.getPersonId(), info.getTeamId(), info.getContestId());

    if (addUserEstimate(info)) {
      return "success";
    } else {
      return "fail";
    }
  }

  @Override
  public List<Estimate> getEstimatesById(String userId) {
    return repository.findAllByAccountId(userId);
  }

  private boolean addUserEstimate(Estimate info) {
    Optional<Competition> competitionTmp = competitionRepository.findById(info.getContestId());
    Optional<Account> accountTmp = accountRepository.findById(info.getPersonId());
    if (competitionTmp.isPresent()) {
      Competition competition = competitionTmp.get();
      List<String> category = competition.getCategory();
      if (accountTmp.isPresent()) {
        Account account = accountTmp.get();
        category.forEach(c -> account.getTagScores().add(new TagScore(c, info.getScore())));
        accountRepository.save(account);
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
