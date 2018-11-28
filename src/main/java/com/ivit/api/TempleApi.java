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
import com.ivit.model.Temple;
import com.ivit.services.CrudService;

@Controller
@RequestMapping("/api/temple")
public class TempleApi {

	@Autowired
	private CrudService<Temple> service;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Temple> list() throws  ServiceException {
		ArrayList<Temple> list = new ArrayList<Temple>();
		for (Temple m : service.list()) {
			list.add(m);
		}
		return list;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Temple obj) {
		try {
			service.create(obj);
			return new ResponseEntity<>("Temple is created successfully", HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<>("Temple is created fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Temple obj) {
		try {
			service.update(id,obj);
			return new ResponseEntity<>("Temple is updated successsfully", HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>("Temple is updated fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Temple is updated successsfully", HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>("Temple is updated fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
