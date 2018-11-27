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

import com.ivit.model.Temple;
import com.ivit.mongo.TempleRepository;

@Controller
@RequestMapping("/api/temple")
public class TempleApi {

	@Autowired
	private TempleRepository repository;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Temple> list() throws UnsupportedEncodingException {
		ArrayList<Temple> list = new ArrayList<Temple>();
		for (Temple m : repository.findAll()) {
			list.add(m);
		}
		return list;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createTemple(@RequestBody Temple obj) {
		repository.save(obj);
		return new ResponseEntity<>("Temple is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateTemple(@PathVariable("id") String id, @RequestBody Temple obj) {
		Optional<Temple> m = repository.findById(id);
		Temple entity = m.get();

		entity.setName(obj.getName());

		repository.save(entity);
		return new ResponseEntity<>("Temple is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Optional<Temple> m = repository.findById(id);
		repository.delete(m.get());
		return new ResponseEntity<>("Temple is deleted successsfully", HttpStatus.OK);
	}


}
