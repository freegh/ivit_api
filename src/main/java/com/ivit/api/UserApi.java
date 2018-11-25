package com.ivit.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivit.model.Temple;


@Controller
@RequestMapping("/api/user")
public class UserApi {

	@RequestMapping(value = "/temple", method = { RequestMethod.GET })
	@ResponseBody
	public List<Temple> list() {
		ArrayList<Temple> list = new ArrayList<Temple>();
		RandomUtils rand = new RandomUtils();
		for(int i=0;i<rand.nextInt(10,50);i++) {
			
			Temple e = new Temple();

			list.add(e);
			
		}
		return list;
	}

}
