package com.ivit.mongo;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ivit.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	Customer findByFirstName(String firstName);

	List<Customer> findByLastName(String lastName);

}