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

    @PostMapping("/account/signup")
    public Account signup(@RequestBody Account account) {
        System.out.println(account);
        return service.signup(account);
    }

    @GetMapping("/account/{id}")
    public Account viewUser(@PathVariable String id) {
        return service.getUserInfo(id);
    }

    @GetMapping("/account/tag/{tag}")
    public List<Account> tagView(@PathVariable String tag) {
        return service.getTagUser(tag);
    }
}
