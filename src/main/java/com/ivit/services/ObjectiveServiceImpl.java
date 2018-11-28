package com.ivit.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivit.exception.ServiceException;
import com.ivit.model.Objective;

@Service
public class ObjectiveServiceImpl extends DBService<Objective> {

	@Override
	public Objective update(String id, Objective obj) throws ServiceException {
		Optional<Objective> m = repository.findById(id);
		Objective entity = m.get();
		entity.setName(obj.getName());
		entity.setUpdateDate(obj.getUpdateDate());
		return repository.save(entity);
	}

}
