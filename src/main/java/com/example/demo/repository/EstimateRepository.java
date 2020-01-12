package com.example.demo.repository;

import com.example.demo.domain.Estimate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EstimateRepository extends CrudRepository<Estimate, String> {
  void deleteEstimateByAccountIdAndPersonIdAndTeamIdAndContestId(String accountId,
      String personId,
      String teamId,
      String contestId);
}
