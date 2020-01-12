package com.example.demo.controller;

import com.example.demo.domain.Estimate;
import com.example.demo.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstimateController {

  @Autowired
  EstimateService estimateService;

  @PostMapping("/estimate")
  public String delete(@RequestBody Estimate info){
    return estimateService.delete(info);
  }
}
