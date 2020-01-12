package com.example.demo.controller;

import com.example.demo.aspect.PerfLogging;
import com.example.demo.domain.Account;
import com.example.demo.domain.Participant;
import com.example.demo.domain.Team;
import com.example.demo.service.TeamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class TeamController {

  @Autowired
  TeamService service;

  /**
   * team 목록 조회
   *
   * @return 현재 등록되어있는  모든 팀을 조회
   */
  @PerfLogging
  @GetMapping("/team")
  public List<Team> getTeams() {
    return service.getTeams();
  }

  /**
   * team 가입 신청한 참가자들 목록을 조회
   * @param teamId 가입신청 참가자들의 목록을 조회할 team id
   * @return 가입 신청 참가자 목록
   */
  @GetMapping("/team/{teamId}")
  public List<Participant> getParticipants(@PathVariable String teamId) {
    return service.getParticipants(teamId);
  }

  /**
   * 특정 공모전 주제에 등록되어 있는 team 목록 조회
   *
   * @param contestId 특정 공모전 주제
   * @return 팀 목록 return
   */
  @GetMapping("/team/contestId/{contestId}")
  public List<Team> getTeamsByContestId(@PathVariable String contestId) {
    return service.getTeamsByContestId(contestId);
  }

  /**
   * 팀 생성
   *
   * @param team 생성할 Team 정보
   * @return 생성한 team 반환
   */
  @PostMapping("/team")
  public Team createTeam(@RequestBody Team team) {
    return service.createTeam(team);
  }

  /**
   * 팀 가입 신청 기능
   *
   * @param participant 신청할 참가자의 정보
   * @return 신청완료된 team 객체 return
   */
  @PostMapping("/team/request")
  public Team request(@RequestBody Participant participant) {
    return service.request(participant);
  }

  /**
   * team 가입 신청 승낙
   * 승낙 후 해당 user에게 message 전송
   *
   * @param participant 가입신청을 승낙할 참가자의 정보
   * @return 신청 승낙 완료된 team 객체 return
   */
  @PostMapping("/team/permit")
  public Team permitSignUp(@RequestBody Participant participant) {
    System.out.println(participant.getTeamId() + "permits" + participant.getAccountId());
    return service.permit(participant);
  }

  /**
   * team 가입 신청 거절
   *
   * @param participant 가입 신청을 거절할 참가자의 정보
   * @return 신청 승낙 거절된 team 객체 return
   */
  @PostMapping("/team/reject")
  public Team rejectSingUp(@RequestBody Participant participant) {
    System.out.println(participant.getTeamId() + "permits" + participant.getAccountId());
    return service.reject(participant);
  }

  /**
   * team 소개 내용 수정
   *
   * @param team 수정할 comment와 team id
   * @return 수정 완료된 team 객체
   */
  @PutMapping("/team/comment")
  public Team updateComment(@RequestBody Team team) {
    return service.updateComment(team);
  }

  /**
   * team 제거, 평가 항목 collection에 사용자 추가
   *
   * @param teamId 제거할 team id
   * @return 성공시 "success", 실패시 "fail"
   */
  @DeleteMapping("/team/{teamId}")
  public String deleteTeam(@PathVariable String teamId){
    return service.deleteTeam(teamId);
  }

}
