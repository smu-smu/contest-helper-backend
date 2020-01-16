package com.example.demo.repository;

import com.example.demo.domain.Account;
import com.example.demo.domain.TagScore;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class AccountCustomRepositoryImpl implements AccountCustomRepository<Account> {

  private final MongoTemplate mongoTemplate;

  public AccountCustomRepositoryImpl(
      MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public List<TagScore> getUserTagScores(String userId) {
    Aggregation agg = Aggregation.newAggregation(
        Aggregation.match(Criteria.where("_id").is(userId)),
        Aggregation.unwind("tagScores"),
        Aggregation.group("tagScores._id")
            .avg("tagScores.score").as("score")
    );
    return mongoTemplate
        .aggregate(agg, Account.class, TagScore.class).getMappedResults();
  }

  @Override
  public List<TagScore> getUserTagScore(String userId, String tagName) {
    Aggregation agg = Aggregation.newAggregation(
        Aggregation.match(Criteria.where("_id").is(userId)),
        Aggregation.unwind("tagScores"),
        Aggregation.group("tagScores._id")
            .avg("tagScores.score").as("score")
    );
    return mongoTemplate.aggregate(agg, Account.class, TagScore.class).getMappedResults();
  }


}
