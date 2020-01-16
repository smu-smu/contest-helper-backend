package com.example.demo.controller;

import com.example.demo.domain.Estimate;
import com.example.demo.service.EstimateService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstimateController {

  private final EstimateService estimateService;

  public EstimateController(EstimateService estimateService) {
    this.estimateService = estimateService;
  }

  /**
   * 해당 사용자의 평가할 목록 조회
   *
   * @param userId 조회할 사용자 id
   * @return 해당 사용자의 평가할 목록
   */
  @GetMapping("/estimate/{userId}")
  public List<Estimate> viewEstimates(@PathVariable String userId) {
    return estimateService.getEstimatesById(userId);
  }

  /**
   * 사용자가 해당 사용자에 대한 평가를 했음
   *
   * @param info
   * @return
   */
  @DeleteMapping("/estimate")
  public String delete(@RequestBody Estimate info) {
    return estimateService.delete(info);
  }
}
