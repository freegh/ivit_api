package com.ivit.mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ivit.model.Member;
import com.ivit.model.Monk;

public interface MemberRepository extends MongoRepository<Member, String>, QuerydslPredicateExecutor<Member> {

}