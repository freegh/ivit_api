package com.ivit.mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ivit.model.Monk;
import com.ivit.model.Objective;

public interface ObjectiveRepository extends MongoRepository<Objective, String>, QuerydslPredicateExecutor<Monk> {

}