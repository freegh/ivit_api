package com.ivit.mongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ivit.model.Monk;

public interface MonkRepository extends MongoRepository<Monk, String>, QuerydslPredicateExecutor<Monk> {
	Monk findBy_id(ObjectId _id);
}