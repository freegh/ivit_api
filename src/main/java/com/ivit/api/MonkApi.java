package com.ivit.api;

import java.util.ArrayList;
import java.util.List;

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
import com.ivit.services.CrudService;

@Controller
@RequestMapping("/api/monk")
public class MonkApi {


	@Autowired
	private CrudService<Monk> service;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Monk> list() throws  ServiceException {
		ArrayList<Monk> list = new ArrayList<Monk>();
		for (Monk m : service.list()) {
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
		try {
			service.delete(id);
			return new ResponseEntity<>("Monk is updated successsfully", HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>("Monk is updated fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	@ResponseBody
	public void test() {
		//repository.deleteAll();


//		QMonk qUser = new QMonk("monk");
//		BooleanExpression predicate = qUser.name.eq("Alice");
//		List<Monk> users = (List<Monk>) repository.findAll(predicate);
//		for (Monk Monk : users) {
//			System.out.println(Monk);
//		}
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
