package com.ivit.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;

@Controller
@RequestMapping("/api/image")
public class ImageApi {

	@Autowired
	RestTemplate restTemplate;
	
	@Value( "${google.map.key}" )
	private String mapkey;
	

	@Autowired
	GridFsOperations gridOperations;
	
	// this variable is used to store ImageId for other actions like: findOne or delete
	private String imageFileId = "5bfd0ad7dcd4f3355839f704";
 
	@GetMapping("/save")
	public String saveFiles() throws FileNotFoundException {

		DBObject metaData = new BasicDBObject();
		//metaData.put("organization", "JavaSampleApproach");

		InputStream iamgeStream = new FileInputStream("C:\\workspace\\MWTools\\src\\main\\webapp\\assets\\images\\demo\\cover2.jpg");
		
		metaData.put("type", "image");
		
		// Store file to MongoDB
		imageFileId = gridOperations.store(iamgeStream, "jsa-logo.png", "image/png", metaData).get().toHexString();
		System.out.println("ImageFileId = " + imageFileId);

		return "Done";
	}
	
	@GetMapping("/retrieve/imagefile")
	public ResponseEntity<InputStreamResource> retrieveImageFile(HttpServletResponse response) throws IOException{

		GridFSFile imageFile = gridOperations.findOne(new Query(Criteria.where("_id").is(imageFileId)));
		GridFsResource res = gridOperations.getResource(imageFile.getFilename());
		return ResponseEntity
				.ok()
				.contentType(MediaType.valueOf(res.getContentType()))
				.body(new InputStreamResource(res.getInputStream()));
	}
	
	@GetMapping("/retrieve/textfiles")
	public String retrieveTextFiles(){
		GridFSFindIterable textFiles = gridOperations.find(new Query(Criteria.where("metadata.type").is("data")));
		
		return "Done";
	}
	
	@GetMapping("/delete/image")
	public String deleteFile(){
		// delete image via id
		gridOperations.delete(new Query(Criteria.where("_id").is(imageFileId)));
		
		return "Done";
	}

}
