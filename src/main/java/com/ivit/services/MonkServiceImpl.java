package com.ivit.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivit.exception.ServiceException;
import com.ivit.model.Monk;

@Service
public class MonkServiceImpl extends DBService<Monk> {

	@Override
	public Monk update(String id, Monk obj) throws ServiceException {
		Optional<Monk> m = repository.findById(id);
		Monk entity = m.get();
		entity.setAge(obj.getAge());
		entity.setComment(obj.getComment());
		entity.setLevel(obj.getLevel());
		entity.setName(obj.getName());
		entity.setNickname(obj.getNickname());
		entity.setSurname(obj.getSurname());
		entity.setYear(obj.getYear());
		return repository.save(entity);
	}

}
