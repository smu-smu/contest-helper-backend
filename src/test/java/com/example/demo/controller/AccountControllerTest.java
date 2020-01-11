package com.example.demo.controller;

import com.example.demo.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AccountControllerTest {
    @MockBean
    AccountService service;

    @Test
    public void crud() {
    }
}