package com.example.demo.repository;

import com.example.demo.domain.Account;
import com.example.demo.domain.TagScore;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AccountRepository extends MongoRepository<Account, String> {

  List<Account> findByTagsContains(String tag);

  List<Account> findByProfilesContains(String profile);

  Optional<Account> findByUserIdAndAndPassword(String userId, String password);

  void deleteAccountByUserId(String userId);

}
