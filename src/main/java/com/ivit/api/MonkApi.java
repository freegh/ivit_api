package com.ivit.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ivit.model.Monk;
import com.ivit.model.QMonk;
import com.ivit.mongo.MonkRepository;
import com.mysema.query.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

@Controller
@RequestMapping("/api/monk")
public class MonkApi {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private MonkRepository repository;
	
	@Value("${google.map.key}")
	private String mapkey;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Monk> list() throws UnsupportedEncodingException {
		ArrayList<Monk> list = new ArrayList<Monk>();
		for (Monk m : repository.findAll()) {
			list.add(m);
		}
		return list;
	}

	
	
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	@ResponseBody
	public void test() {
		repository.deleteAll();

		// save a couple of Monks
		repository.save(new Monk("Alice", "Smith", "Smith", 1,2, "Smith", "Smith"));
		repository.save(new Monk("bb", "bb", "cc", 1,2, "ee", "ff"));
		// fetch all Monks
		System.out.println("Monks found with findAll():");
		System.out.println("-------------------------------");
		for (Monk Monk : repository.findAll()) {
			System.out.println(Monk);
		}
		System.out.println();
		
		QMonk qUser = new QMonk("monk");
		BooleanExpression predicate = qUser.name.eq("Alice");
		List<Monk> users = (List<Monk>) repository.findAll(predicate);
		for (Monk Monk : users) {
			System.out.println(Monk);
		}
/*
		// fetch an individual Monk
		System.out.println("Monk found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Monks found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Monk Monk : repository.findByLastName("Smith")) {
			System.out.println(Monk);
		}*/

	}

}
