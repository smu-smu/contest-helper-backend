package com.example.demo.controller;

import com.example.demo.domain.Account;
import com.example.demo.domain.TagScore;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService service;

    /**
     * GET
     **/

    // 전체 account 조회
    @GetMapping("/account")
    public List<Account> getUsers() {
        return service.getUsers();
    }

    // user id를 기준으로 특정 user 찾기
    @GetMapping("/account/{userId}")
    public Account getUserByUser_Id(@PathVariable String userId) {
        return service.getUserInfo(userId);
    }

    // 특정 tag를 즐겨찾기한 user 목록
    @GetMapping("/account/tag/{tag}")
    public List<Account> getUsersByTag(@PathVariable String tag) {
        return service.getUsersByTag(tag);
    }

    // 특정 profile이 있는 user 목록
    @GetMapping("/account/tag/{profile}")
    public List<Account> getUsersByProfile(@PathVariable String profile) {
        return service.getUsersByProfile(profile);
    }

    // 특정 user의 profile
    @GetMapping("/account/profile/{userId}")
    public List<String> getUserProfile(@PathVariable String userId) {
        return service.getUserProfile(userId);
    }

    /**
     * POST
     **/
    @PostMapping("/account/signup")
    public Account signup(@RequestBody Account account) {
        System.out.println(account);
        return service.signup(account);
    }

    @PostMapping("/account/tag")
    public Account addTagsToUser(@RequestBody Account account) {
        return service.addTagsToUser(account.getUserId(), account.getFavorites());
    }

    @PostMapping("/account/profiles")
    public Account addProfileToUser(@RequestBody Account account) {
        return service.addProfilesToUser(account.getUserId(), account.getProfiles());
    }

    @PostMapping("/account/tagscore/{userId}")
    public Account updateTagScore(@RequestBody TagScore tagScore, @PathVariable String userId){
        return service.updateTagScores(tagScore,userId);
    }
}
