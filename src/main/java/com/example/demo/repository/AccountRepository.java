package com.example.demo.repository;

import com.example.demo.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {

  List<Account> findByFavoritesContains(String tag);

  List<Account> findByProfilesContains(String profile);

  Optional<Account> findByUserIdAndAndPassword(String userId, String password);
}
