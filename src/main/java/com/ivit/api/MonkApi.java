package com.ivit.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ivit.google.map.model.Result;
import com.ivit.model.Monk;

@Controller
@RequestMapping("/api/monk")
public class MonkApi {

	@Autowired
	RestTemplate restTemplate;

	@Value("${google.map.key}")
	private String mapkey;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Monk> list(@RequestParam("lat") String lat, @RequestParam("lng") String lng,
			@RequestParam(value = "name", required = false) String name) throws UnsupportedEncodingException {
		ArrayList<Monk> list = new ArrayList<Monk>();

		Monk e = new Monk();
		list.add(e);
		return list;
	}

}
