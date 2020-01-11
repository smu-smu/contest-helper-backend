package com.example.demo.controller;

import com.example.demo.domain.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService service;

    @GetMapping("/account")
    public List<Account> getUsers(){
        return service.getUsers();
    }

    @PostMapping("/account/signup")
    public Account signup(@RequestBody Account account) {
        System.out.println(account);
        return service.signup(account);
    }

    @GetMapping("/account/{userId}")
    public Account getUserByUser_Id(@PathVariable String userId) {
        return service.getUserInfo(userId);
    }

    @GetMapping("/account/tag/{tag}")
    public List<Account> getUsersByTag(@PathVariable String tag) {
        return service.getUsersByTag(tag);
    }

    @GetMapping("/account/tag/{profile}")
    public List<Account> getUsersByProfile(@PathVariable String profile) {
        return service.getUsersByProfile(profile);
    }

    @GetMapping("/account/profile/{userId}")
    public List<String> getUserProfile(@PathVariable String userId){
        return service.getUserProfile(userId);
    }
}
