package com.example.demo.repository;

import com.example.demo.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {

    List<Account> findByFavoritesContains(String tag);
}
