package com.ivit.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivit.exception.ServiceException;
import com.ivit.model.Booking;

@Service
public class BookingServiceImpl extends DBService<Booking> {

	@Override
	public Booking update(String id, Booking obj) throws ServiceException {
		Optional<Booking> m = repository.findById(id);
		Booking entity = m.get();

		return repository.save(entity);
	}

}
