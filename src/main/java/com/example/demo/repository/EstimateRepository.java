package com.example.demo.repository;

import com.example.demo.domain.Estimate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstimateRepository extends CrudRepository<Estimate, String> {

  List<Estimate> findByTeamId(String teamId);

  List<Estimate> findByAccountId(String userId);
}
