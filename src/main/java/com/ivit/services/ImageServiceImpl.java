package com.ivit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;

import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	GridFsOperations gridOperations;
	
	@Override
	public GridFsResource readImage(String id) {
		GridFSFile imageFile = gridOperations.findOne(new Query(Criteria.where("_id").is(id)));
		return gridOperations.getResource(imageFile.getFilename());
	}
	
	@Override
	public void delete(String id) {
		gridOperations.delete(new Query(Criteria.where("_id").is(id)));
	}
	
}
