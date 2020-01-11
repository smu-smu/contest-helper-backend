package com.example.demo.repository;

import com.example.demo.domain.Participant;
import com.example.demo.domain.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TeamRepository extends MongoRepository<Team, String> {

    @Query("{participants}")
    Participant findParticipantByParticipantId(String participantId);
}
