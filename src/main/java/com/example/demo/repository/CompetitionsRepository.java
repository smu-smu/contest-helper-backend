package com.example.demo.repository;

import com.example.demo.domain.Competitions;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionsRepository extends CrudRepository<Competitions, String> {
}
