package com.example.demo.controller;

import com.example.demo.aspect.PerfLogging;
import com.example.demo.domain.Participant;
import com.example.demo.domain.Team;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class TeamController {

    @Autowired
    TeamService service;

    @PostMapping("/team")
    public Team createTeam(@RequestBody Team team) {
        return service.createTeam(team);
    }

    @PerfLogging
    @GetMapping("/team")
    public List<Team> getTeams() {
        return service.getTeams();
    }

    @GetMapping("/team/{teamId}/participant/{participantId}")
    public Participant getParticipants(@PathVariable String teamId, @PathVariable String participantId) {
        return service.getParticipantById(teamId, participantId);
    }

    @GetMapping("/team/{teamId}")
    public List<Participant> getParticipants(@PathVariable String teamId) {
        return service.getParticipants(teamId);
    }

    @GetMapping("/team/contestId/{contestId}")
    public List<Team> getTeamsByContestId(@PathVariable String contestId) {
        return service.getTeamsByContestId(contestId);
    }

    @DeleteMapping("/team/{name}")
    public Team remove(@PathVariable String name){
        return service.remove(name);
    }


    /** POST **/

    @PostMapping("/team/request")
    public Team request(@RequestBody Participant participant) {
        return service.request(participant);
    }

    @PostMapping("/team/permit")
    public Team permitSignUp(@RequestBody Participant participant){
        System.out.println(participant.getTeamId() + "permits" + participant.getAccountId());
        return service.permit(participant);
    }

    @PostMapping("/team/reject")
    public Team rejectSingUp(@RequestBody Participant participant){
        System.out.println(participant.getTeamId() + "permits" + participant.getAccountId());
        return service.reject(participant);
    }

    @PostMapping("/team/comment")
    public Team updateComment(@RequestBody Team team){
        return service.updateComment(team);
    }

}
