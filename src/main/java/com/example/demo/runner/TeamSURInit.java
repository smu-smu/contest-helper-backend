package com.example.demo.runner;

import com.example.demo.repository.TeamSURRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class TeamSURInit implements ApplicationRunner {

    @Autowired
    TeamSURRepository teamSURRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        teamSURRepository.deleteAll();
    }
}
