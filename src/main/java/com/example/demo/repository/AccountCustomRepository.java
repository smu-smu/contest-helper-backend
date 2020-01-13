package com.example.demo.repository;

import com.example.demo.domain.TagScore;
import java.util.List;

public interface AccountCustomRepository<T> {

  List<TagScore> getUserTagScores(String userId);

  List<TagScore> getUserTagScore(String userId, String tagName);
}
