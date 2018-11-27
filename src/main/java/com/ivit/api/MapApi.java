package com.ivit.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ivit.google.map.model.Places;
import com.ivit.google.map.model.Result;
import com.ivit.model.Temple;

@Controller
@RequestMapping("/api/map")
public class MapApi {

	@Autowired
	RestTemplate restTemplate;
	
	@Value( "${google.map.key}" )
	private String mapkey;
	

	@RequestMapping(value = "/temple", method = { RequestMethod.GET })
	@ResponseBody
	public List<Temple> list(@RequestParam("lat") String lat, @RequestParam("lng") String lng, @RequestParam(value="name",required=false) String name) throws UnsupportedEncodingException {
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("location", lat + "," + lng)
				.queryParam("radius", "1000")
				.queryParam("key", mapkey)
				.queryParam("language", "th")
				.queryParam("name", name);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		String newUrl = URLDecoder.decode(builder.toUriString(), "UTF-8"); 
		HttpEntity<Places> response = restTemplate.exchange(newUrl, HttpMethod.GET, entity,
				Places.class);
		ArrayList<Temple> list = new ArrayList<Temple>();
		if (response.getBody().getResults() != null) {
			for (Result p : response.getBody().getResults()) {
				Temple e = new Temple();
				System.out.println(p.getName());
				e.setName(p.getName());
				list.add(e);
			}
		}
		return list;
	}

}
