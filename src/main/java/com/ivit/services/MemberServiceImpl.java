package com.ivit.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivit.exception.ServiceException;
import com.ivit.model.Member;

@Service
public class MemberServiceImpl extends DBService<Member> {
	
	@Override
	public Member update(String id, Member obj) throws ServiceException {
		Optional<Member> m = repository.findById(id);
		Member entity = m.get();
		entity.setName(obj.getName());
		entity.setUpdateDate(obj.getUpdateDate());
		return repository.save(entity);
	}


}
