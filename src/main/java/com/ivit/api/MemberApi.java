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

import com.ivit.model.Member;
import com.ivit.mongo.MemberRepository;

@Controller
@RequestMapping("/api/member")
public class MemberApi {

	@Autowired
	private MemberRepository repository;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Member> list() throws UnsupportedEncodingException {
		ArrayList<Member> list = new ArrayList<Member>();
		for (Member m : repository.findAll()) {
			list.add(m);
		}
		return list;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createMember(@RequestBody Member obj) {
		repository.save(obj);
		return new ResponseEntity<>("Member is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateMember(@PathVariable("id") String id, @RequestBody Member obj) {
		Optional<Member> m = repository.findById(id);
		Member entity = m.get();

		entity.setName(obj.getName());

		repository.save(entity);
		return new ResponseEntity<>("Member is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Optional<Member> m = repository.findById(id);
		repository.delete(m.get());
		return new ResponseEntity<>("Member is deleted successsfully", HttpStatus.OK);
	}


}
