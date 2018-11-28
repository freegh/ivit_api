package com.ivit.services;

import com.ivit.exception.ServiceException;

public interface CrudService<E> {
	public E create(E obj) throws ServiceException;
	public E read(String id) throws ServiceException;
	public E update(String id,E obj) throws ServiceException;
	public void delete(String id) throws ServiceException;
}
