package com.example.demo.repository;

import com.example.demo.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository repository;

    @Test
    public void crud(){
        Account account = new Account();
        account.setName("kts");
        account.setUserId("1");
        account.setPassword("kts");
        account.addProfile("profiles");

        repository.save(account);

        Optional<Account> byId = repository.findById(account.getUserId());
        System.out.println(byId.get());
    }

    @Test
    public void addFavorite(){

    }

}