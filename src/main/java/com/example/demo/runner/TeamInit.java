package com.example.demo.runner;

import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class TeamInit implements ApplicationRunner {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        teamRepository.deleteAll();
    }

}
