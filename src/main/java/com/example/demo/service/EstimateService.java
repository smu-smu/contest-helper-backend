package com.example.demo.service;

import com.example.demo.domain.Estimate;
import java.util.List;

public interface EstimateService {

  String delete(Estimate info);

  List<Estimate> getEstimatesById(String userId);
}
