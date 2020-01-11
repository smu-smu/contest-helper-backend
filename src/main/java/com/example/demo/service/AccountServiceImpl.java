package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public Account getUserInfo(String userId) {
        return repository.findById(userId).orElseGet(()->new Account());
    }

    @Override
    public Account signup(Account account) {
        return repository.save(account);
    }

    @Override
    public List<Account> getTagUser(String tag) {
        return repository.findByFavoritesContains(tag);
    }


}
