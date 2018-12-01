package com.ivit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivit.api.web.model.DatatableRequest;
import com.ivit.api.web.model.DatatableResult;
import com.ivit.exception.ServiceException;
import com.ivit.model.Booking;
import com.ivit.services.BookingServiceImpl;

@Controller
public class DashboardController {
	
	@Autowired
	private BookingServiceImpl booking;
	
	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public void index(Model model, HttpServletResponse response) {
		try {
			response.sendRedirect("/dashboard");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value ="/dashboard", method = { RequestMethod.GET })
	public String dashboard(Model model, HttpServletResponse response) {

		return "../dashboard/index";
	}

	@RequestMapping(value = "/listBooking", method = { RequestMethod.GET })
	@ResponseBody
	public DatatableResult<Booking> listBooking(DatatableRequest search) throws ServiceException {
		DatatableResult<Booking> result = new DatatableResult<Booking>();
		List<Booking> data = booking.list();

		ArrayList<Booking> list = null;
		if (data != null && !data.isEmpty()) {
			list = new ArrayList<Booking>();

			for (int i=0;i<data.size();i++) {

					list.add(data.get(i));

			}
			result.setRecordsFiltered(data.size());
			result.setRecordsTotal(data.size());
		}

		result.setData(list);
		result.setDraw(search.getDraw());

		return result;
	}
}
