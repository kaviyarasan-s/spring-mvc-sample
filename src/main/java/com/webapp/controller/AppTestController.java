package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class AppTestController {

	@RequestMapping("/test")
	@ResponseBody
	public String checkRunningStatus() {
		return "Running!!!";
	}

	@RequestMapping("/home")
	public ModelAndView home(ModelAndView view) {
		view.setViewName("home.jsp");
		return view;
	}
	
	@RequestMapping("/welcome")
	public ModelAndView welcome(ModelAndView view) {
		view.setViewName("welcome.html");
		return view;
	}
}
