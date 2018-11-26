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

import com.ivit.model.Customer;
import com.ivit.model.Monk;
import com.ivit.mongo.CustomerRepository;

@Controller
@RequestMapping("/api/monk")
public class MonkApi {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private CustomerRepository repository;
	
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

	
	
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	@ResponseBody
	public void test() {
		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

	}

}
