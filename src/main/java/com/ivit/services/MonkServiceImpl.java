package com.ivit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivit.exception.ServiceException;
import com.ivit.model.Monk;
import com.ivit.mongo.MonkRepository;

@Service
public class MonkServiceImpl implements CrudService<Monk> {
	@Autowired
	private MonkRepository repository;

	@Override
	public Monk create(Monk obj) throws ServiceException {
		Monk monk = repository.save(obj);
		return monk;
	}

	@Override
	public Monk read(String id) throws ServiceException {
		Optional<Monk> op = repository.findById(id);
		return op.get();
	}

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

	@Override
	public void delete(String id) throws ServiceException {
		Optional<Monk> m = repository.findById(id);
		repository.delete(m.get());
	}

	public void list(Monk obj) throws ServiceException {
	}
}
