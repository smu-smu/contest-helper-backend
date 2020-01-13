package com.example.demo.repository;

import com.example.demo.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PageRepository extends MongoRepository<Page, String> {

}
