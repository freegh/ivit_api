package com.ivit.mongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ivit.model.Monk;

public interface MonkRepository extends MongoRepository<Monk, String> {
	Monk findBy_id(ObjectId _id);
}