package com.ivit.mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ivit.model.Monk;
import com.ivit.model.Temple;

public interface TempleRepository extends MongoRepository<Temple, String>, QuerydslPredicateExecutor<Temple> {

}