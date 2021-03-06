package com.example.demo.repository;

import com.example.demo.domain.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String>,
    AccountCustomRepository<Account> {

  List<Account> findByTagsContains(String tag);

  List<Account> findByProfilesContains(String profile);

  Optional<Account> findByUserIdAndAndPassword(String userId, String password);

  void deleteAccountByUserId(String userId);

}
