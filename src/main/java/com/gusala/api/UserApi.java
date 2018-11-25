package com.gusala.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gusala.model.Promotion;


@Controller
@RequestMapping("/api/user")
public class UserApi {

	@RequestMapping(value = "/promotion", method = { RequestMethod.GET })
	@ResponseBody
	public List<Promotion> list() {
		ArrayList<Promotion> list = new ArrayList<Promotion>();
		RandomUtils rand = new RandomUtils();
		for(int i=0;i<rand.nextInt(10,50);i++) {
			
			Promotion e = new Promotion();

			list.add(e);
			
		}
		return list;
	}

}
