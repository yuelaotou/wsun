package com.wsun.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class IndexController {

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mv.addObject("loginTime", sdf.format(new Date()));
		mv.addObject("userName", "杨光");
		mv.setViewName("index");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
	public String home() {
		return "home";
	}

	@ResponseBody
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout() {
		return "logout";
	}
}
