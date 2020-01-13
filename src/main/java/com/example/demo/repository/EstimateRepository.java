package com.example.demo.repository;

import com.example.demo.domain.Estimate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstimateRepository extends MongoRepository<Estimate, String> {

  List<Estimate> findAllByAccountId(String accountId);

  void deleteEstimateByAccountIdAndPersonIdAndTeamIdAndContestId(
      String accountId,
      String personId,
      String teamId,
      String contestId);
}
