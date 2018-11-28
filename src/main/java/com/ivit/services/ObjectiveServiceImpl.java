package com.ivit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivit.exception.ServiceException;
import com.ivit.model.Objective;
import com.ivit.mongo.ObjectiveRepository;

@Service
public class ObjectiveServiceImpl implements CrudService<Objective> {
	@Autowired
	private ObjectiveRepository repository;

	@Override
	public Objective create(Objective obj) throws ServiceException {
		Objective monk = repository.save(obj);
		return monk;
	}

	@Override
	public Objective read(String id) throws ServiceException {
		Optional<Objective> op = repository.findById(id);
		return op.get();
	}

	@Override
	public Objective update(String id, Objective obj) throws ServiceException {
		Optional<Objective> m = repository.findById(id);
		Objective entity = m.get();
		entity.setName(obj.getName());
		entity.setUpdateDate(obj.getUpdateDate());
		return repository.save(entity);
	}

	@Override
	public void delete(String id) throws ServiceException {
		Optional<Objective> m = repository.findById(id);
		repository.delete(m.get());
	}

	public void list(Objective obj) throws ServiceException {
	}
}
