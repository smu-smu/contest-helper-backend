package com.example.demo.repository;

import com.example.demo.domain.Estimate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EstimateRepository extends CrudRepository<Estimate, String> {
  List<Estimate> findByTeamId(String teamId);
}
