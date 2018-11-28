package com.ivit.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivit.exception.ServiceException;
import com.ivit.model.Monk;
import com.ivit.model.QMonk;
import com.ivit.mongo.MonkRepository;
import com.ivit.services.CrudService;
import com.querydsl.core.types.dsl.BooleanExpression;

@Controller
@RequestMapping("/api/monk")
public class MonkApi {

	@Autowired
	private MonkRepository repository;

	@Autowired
	private CrudService<Monk> service;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Monk> list() throws UnsupportedEncodingException {
		ArrayList<Monk> list = new ArrayList<Monk>();
		for (Monk m : repository.findAll()) {
			list.add(m);
		}
		return list;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createMonk(@RequestBody Monk obj) {
		try {
			service.create(obj);
			return new ResponseEntity<>("Monk is created successfully", HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<>("Monk is created fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateMonk(@PathVariable("id") String id, @RequestBody Monk obj) {
		try {
			service.update(id,obj);
			return new ResponseEntity<>("Monk is updated successsfully", HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>("Monk is updated fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Optional<Monk> m = repository.findById(id);
		repository.delete(m.get());
		return new ResponseEntity<>("Monk is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	@ResponseBody
	public void test() {
		repository.deleteAll();

		// save a couple of Monks
		repository.save(new Monk("Alice", "Smith", "Smith", 1, 2, "Smith", "Smith"));
		repository.save(new Monk("bb", "bb", "cc", 1, 2, "ee", "ff"));
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
		 * // fetch an individual Monk
		 * System.out.println("Monk found with findByFirstName('Alice'):");
		 * System.out.println("--------------------------------");
		 * System.out.println(repository.findByFirstName("Alice"));
		 * 
		 * System.out.println("Monks found with findByLastName('Smith'):");
		 * System.out.println("--------------------------------"); for (Monk Monk :
		 * repository.findByLastName("Smith")) { System.out.println(Monk); }
		 */

	}

}
