package com.example.demo.service;

import com.example.demo.domain.Team;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Team getTeamInfo(String id) {
        return teamRepository.findById(id).get();
    }
}
