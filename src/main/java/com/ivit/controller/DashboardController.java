package com.ivit.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {
    @RequestMapping("/")
    public void index(Model model,HttpServletResponse response) {
    	try {
			response.sendRedirect("/dashboard");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping("/dashboard")
    public String dashboard(Model model,HttpServletResponse response) {

    	return "../dashboard/index";
    }

}
