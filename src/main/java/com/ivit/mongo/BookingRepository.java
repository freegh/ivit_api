package com.ivit.mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ivit.model.Booking;
import com.ivit.model.Monk;

public interface BookingRepository extends MongoRepository<Booking, String>, QuerydslPredicateExecutor<Monk> {

}