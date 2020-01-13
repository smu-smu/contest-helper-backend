package com.example.demo.controller;

import com.example.demo.aspect.PerfLogging;
import com.example.demo.domain.Account;
import com.example.demo.domain.Message;
import com.example.demo.domain.TagScore;
import com.example.demo.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AccountController {

  @Autowired
  AccountService service;


  /**
   * 전체 사용자 조회
   *
   * @return 전체 사용자 목록
   */
  @GetMapping("/account")
  public List<Account> getUsers() {
    return service.getUsers();
  }

  /**
   * 특정 id를 가진 사용자 정보 조회
   *
   * @param userId 특정 사용자 id
   * @return 특정 사용자의 정보+
   */
  // user id를 기준으로 특정 user 찾기
  @GetMapping("/account/{userId}")
  public Account getUserByUserId(@PathVariable String userId) {
    return service.getUserInfo(userId);
  }

  /**
   * 특정 id를 가진 사용자의 즐겨찾기 목록 조회
   *
   * @param userId 특정 사용자의 id
   * @return 특정 사용자의 즐겨찾기 목록
   */
  @GetMapping("/account/{userId}/tags")
  public List<String> getUserTagsByUserId(@PathVariable String userId) {
    return service.getUserTags(userId);
  }

  /**
   * 특정 tag를 즐겨찾기한 user 목록 조회
   *
   * @param tag 특정 tag
   * @return 특정 tag를 즐겨찾기한 사용자 목록 조회
   */
  @GetMapping("/account/tag/{tag}")
  public List<Account> getUsersByTag(@PathVariable String tag) {
    return service.getUsersByTag(tag);
  }

  /**
   * 특정 경력이 있는 user 목록
   *
   * @param profile 특정 경력
   * @return 특정 경력을 소유하고 있는 user 목록
   */
  @GetMapping("/account/profile/{profile}")
  public List<Account> getUsersByProfile(@PathVariable String profile) {
    return service.getUsersByProfile(profile);
  }

  /**
   * 특정 user의 경력 목록 조회
   *
   * @param userId 특정 user id
   * @return 특정 user의 경력 목록
   */
  @GetMapping("/account/{userId}/profile")
  public List<String> getUserProfile(@PathVariable String userId) {
    return service.getUserProfile(userId);
  }

  /**
   * 특정 user의 메시지 목록 조회
   *
   * @param userId 특정 user id
   * @return 특정 user의 message 목록
   */
  @GetMapping("/account/{userId}/message")
  public List<Message> getUserMessages(@PathVariable String userId) {
    return service.getUserMessages(userId);
  }

  /**
   * 특정 user의 팀 목록 조회
   *
   * @param userId 특정 user id
   * @return 특정 user의 team 목록록
   */
  @GetMapping("/account/{userId}/team")
  public List<String> getUserTeam(@PathVariable String userId) {
    return service.getUserTeam(userId);
  }

  /**
   * 특정 user의 tag 스코어 목록 조회
   *
   * @param userId 특정 user id
   * @return 특정 user의 tagScore 목록
   */
  @GetMapping("/account/{userId}/tagScore")
  public List<TagScore> getUserTagScores(@PathVariable String userId) {
    return service.getUserTagScores(userId);
  }

  /**
   * 특정 user의 특정 tag 스코어 점수 조회
   *
   * @param userId  특정 user id
   * @param tagName 특정 tag 이름
   * @return 특정 user의 tagScore 점수
   */
  @GetMapping("/account/{userId}/tagScore/{tagName}")
  public Double getUserTagScore(@PathVariable String userId, @PathVariable String tagName) {
    return service.getUserTagScore(userId, tagName);
  }

  /**
   * 사용자가 포함되어 있는 team 목록 반환
   *
   * @param userId 조회할 사용자 id
   * @return team 목록 반환 사용자 id가 존재하지 않으면 빈 Account 객체 return
   */
  @GetMapping("/account/team/{userId}")
  public List<String> getTeamsById(@PathVariable String userId) {
    return service.getTeamsById(userId);
  }


  /**
   * 사용자 인증 (id, pw) 모두 일치해야 한다.
   *
   * @param account 사용자 로그인 정보
   * @return id와 pw가 일치하지 않으면 필드값이 null 인 Account 객체 return
   */
  @PerfLogging
  @PostMapping("/account/signin")
  public Account signin(@RequestBody Account account) {
    return service.signin(account);
  }

  /**
   * 사용자 회원가입
   *
   * @param account 화원 가입할 사용자 정보
   * @return 가입비할 사용자 id가 있으면 null Account 객체를 return id가 없다면 회원가입후 사용자 정보 return
   */
  @PerfLogging
  @PostMapping("/account/signup")
  public Account signup(@RequestBody Account account) {
    return service.signup(account);
  }

  /**
   * 사용자 계정에 선호 tag 추가
   *
   * @param account userId, tag만 기입된 Account
   * @return 추가 완료된 Account 객체 정보 return 사용자 id가 존재하지 않으면 빈 Account 객체 return
   */
  @PostMapping("/account/tag")
  public Account addTagsToUser(@RequestBody Account account) {
    return service.addTagsToUser(account.getUserId(), account.getTags());
  }

  /**
   * 사용자 계정에 이력(profile) 추가
   *
   * @param account userId, profiles만 기입된 Account
   * @return 추가 완료된 Account 객체 정보 return 사용자 id가 존재하지 않으면 빈 Account 객체 return
   */
  @PostMapping("/account/profiles")
  public Account addProfileToUser(@RequestBody Account account) {
    return service.addProfilesToUser(account.getUserId(), account.getProfiles());
  }

  /**
   * 사용자 계정에 message 추가
   *
   * @param account userId, message만 기입된 Account
   * @return 추가 완료된 Account 객체 정보 return 사용자 id가 존재하지 않으면 빈 Account 객체 return
   */
  @PostMapping("/account/message")
  public Account sendMessageToUser(@RequestBody Account account) {
    return service.sendMessage(account);
  }

  /**
   * 사용자의 tagScore에 새로운 tagScore 추가
   *
   * @param userId   추가할 사용자 id
   * @param tagScore 추가할 tagScore 정보
   * @return 추가된 Account 객체 사용자 id가 존재하지 않으면 빈 Account 객체 return
   */
  @PostMapping("/account/tagScore/{userId}")
  public Account addTagScore(@PathVariable String userId, @RequestBody TagScore tagScore) {
    return service.addTagScore(userId, tagScore);
  }

  /**
   * 사용자 삭제
   *
   * @param userId 삭제할 user id
   * @return 성공시 "success", 실패시 "fail"
   */
  @DeleteMapping("/account/{userId}")
  public String deleteUser(@PathVariable String userId) {
    return service.deleteUser(userId);
  }

  /**
   * 사용자 계정의 message 삭제
   *
   * @param userId    삭제할 userId
   * @param messageId 삭제할 messageId
   * @return 삭제 완료한 Account 객체 정보 return 사용자 id가 존재하지 않으면 빈 Account 객체 return
   */
  @DeleteMapping("/account/message/{userId}/{messageId}")
  public Account deleteMessage(@PathVariable String userId, @PathVariable Integer messageId) {
    return service.deleteMessage(userId, messageId);
  }
}
