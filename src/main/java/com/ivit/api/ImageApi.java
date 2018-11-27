package com.ivit.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.ivit.services.ImageService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Controller
@RequestMapping("/api/image")
public class ImageApi {

	@Autowired
	RestTemplate restTemplate;
	
	@Value( "${google.map.key}" )
	private String mapkey;
	

	@Autowired
	GridFsOperations gridOperations;
	
	@Autowired
	ImageService service;
	// this variable is used to store ImageId for other actions like: findOne or delete
	private String imageFileId = "5bfd0ad7dcd4f3355839f704";
 
	@GetMapping("/save")
	public String saveFiles() throws MalformedURLException, IOException  {

		DBObject metaData = new BasicDBObject();
		//metaData.put("organization", "JavaSampleApproach");

		//InputStream iamgeStream = new FileInputStream("C:\\workspace\\MWTools\\src\\main\\webapp\\assets\\images\\demo\\cover3.jpg");
		InputStream input = new URL("https://maps.googleapis.com/maps/api/place/photo?maxwidth=250&photoreference=CmRaAAAA-RswJA-481B3mlIQnvTV5PFnuOlkzvLmXdVHL2MCRwOLoV3LfEfUbLeLZB_VsX4T9asAsN3pPW-bMf_txrx65SnYd-we3sBS1eGRCZzQFLteh_E9DK0pQM_FrOp-siW3EhAR8IrANu6DOPO0Wjjz0YTYGhSAgvI_YFbPy-2-2K8z5cYXn8kalA&key="+mapkey).openStream();
		
		metaData.put("type", "image");
		
		// Store file to MongoDB
		imageFileId = gridOperations.store(input, "jsa-logo.png", "image/png", metaData).get().toHexString();
		System.out.println("ImageFileId = " + imageFileId);

		return "Done";
	}
	
	@RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> readImage(@PathVariable("id") String id) throws IllegalStateException, IOException {
		GridFsResource res = service.readImage(id);
		return ResponseEntity
				.ok()
				.contentType(MediaType.valueOf(res.getContentType()))
				.body(new InputStreamResource(res.getInputStream()));
	}
	
	@GetMapping("/delete/image")
	public String deleteFile(@PathVariable("id") String id){
		service.delete(id);
		return "Done";
	}

}
