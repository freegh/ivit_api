package com.ivit.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivit.exception.ServiceException;
import com.ivit.model.Temple;

@Service
public class TempleServiceImpl extends DBService<Temple> {

	@Override
	public Temple update(String id, Temple obj) throws ServiceException {
		Optional<Temple> m = repository.findById(id);
		Temple entity = m.get();
		entity.setName(obj.getName());
		entity.setUpdateDate(obj.getUpdateDate());
		return repository.save(entity);
	}

}
