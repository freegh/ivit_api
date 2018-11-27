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

import com.ivit.model.Objective;
import com.ivit.mongo.ObjectiveRepository;

@Controller
@RequestMapping("/api/objective")
public class ObjectiveApi {

	@Autowired
	private ObjectiveRepository repository;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Objective> list() throws UnsupportedEncodingException {
		ArrayList<Objective> list = new ArrayList<Objective>();
		for (Objective m : repository.findAll()) {
			list.add(m);
		}
		return list;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createObjective(@RequestBody Objective obj) {
		repository.save(obj);
		return new ResponseEntity<>("Objective is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateObjective(@PathVariable("id") String id, @RequestBody Objective obj) {
		Optional<Objective> m = repository.findById(id);
		Objective entity = m.get();

		entity.setName(obj.getName());
		entity.setUpdateDate(obj.getUpdateDate());
		repository.save(entity);
		return new ResponseEntity<>("Objective is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Optional<Objective> m = repository.findById(id);
		repository.delete(m.get());
		return new ResponseEntity<>("Objective is deleted successsfully", HttpStatus.OK);
	}


}
