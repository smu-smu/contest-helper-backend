package com.example.demo.repository;

import com.example.demo.domain.Estimate;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class EstimateRepositoryTest {

  @Autowired
  EstimateRepository repository;

  @Test
  public void read() {
    Estimate estimate1 = new Estimate();
    estimate1.setAccountId("1");
    estimate1.setContestId("contest");
    estimate1.setPersonId("2");
    estimate1.setTeamId("team");

    Estimate estimate2 = new Estimate();
    estimate2.setAccountId("1");
    estimate2.setContestId("contest");
    estimate2.setPersonId("3");
    estimate2.setTeamId("team");

    Estimate estimate3 = new Estimate();
    estimate3.setAccountId("2");
    estimate3.setContestId("contest");
    estimate3.setPersonId("1");
    estimate3.setTeamId("team");

    repository.save(estimate1);
    repository.save(estimate2);
    repository.save(estimate3);

    List<Estimate> allByAccountId = repository.findAllByAccountId("2");
    allByAccountId.forEach(System.out::println);
  }
}