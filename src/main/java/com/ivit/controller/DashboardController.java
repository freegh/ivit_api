package com.ivit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivit.api.web.model.DatatableRequest;
import com.ivit.api.web.model.DatatableResult;
import com.ivit.model.Monk;

@Controller
public class DashboardController {
	@RequestMapping("/")
	public void index(Model model, HttpServletResponse response) {
		try {
			response.sendRedirect("/dashboard");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/dashboard")
	public String dashboard(Model model, HttpServletResponse response) {

		return "../dashboard/index";
	}
/*
	@RequestMapping(value = "/listVdo", method = { RequestMethod.POST })
	@ResponseBody
	public DatatableResult<Monk> listVdo(DatatableRequest search) {
		DatatableResult<Monk> result = new DatatableResult<Monk>();
		List<Monk> vdolist = mQUtils.readVDOLog();

		ArrayList<Monk> list = null;
		if (vdolist != null && !vdolist.isEmpty()) {
			list = new ArrayList<Monk>();

			for (int i=0;i<vdolist.size();i++) {

					list.add(vdolist.get(i));

			}
			result.setRecordsFiltered(vdolist.size());
			result.setRecordsTotal(vdolist.size());
		}

		result.setData(list);
		result.setDraw(search.getDraw());

		return result;
	}*/
}
