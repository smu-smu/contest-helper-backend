package com.example.demo.runner;

import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AcconutInit implements ApplicationRunner {

    @Autowired
    AccountRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        repository.deleteAll();
    }
}
