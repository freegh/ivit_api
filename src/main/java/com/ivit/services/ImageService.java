package com.ivit.services;

import org.springframework.data.mongodb.gridfs.GridFsResource;

public interface ImageService {

	GridFsResource readImage(String id);

	void delete(String id);

}
