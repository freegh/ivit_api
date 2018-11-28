package com.ivit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ivit.exception.ServiceException;

public abstract class DBService<E> implements CrudService<E> {
	@Autowired
	protected MongoRepository<E, String> repository;

	@Override
	public E create(E obj) throws ServiceException {
		E monk = repository.save(obj);
		return monk;
	}

	@Override
	public E read(String id) throws ServiceException {
		Optional<E> op = repository.findById(id);
		return op.get();
	}

	@Override
	public void delete(String id) throws ServiceException {
		Optional<E> m = repository.findById(id);
		repository.delete(m.get());
	}
}
