package com.example.demo.runner;

import com.example.demo.repository.TeamRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TeamInit implements ApplicationRunner {

  private final TeamRepository teamRepository;

  public TeamInit(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }


  @Override
  public void run(ApplicationArguments args) throws Exception {
    teamRepository.deleteAll();
  }
}
