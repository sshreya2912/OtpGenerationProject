package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Otp;
import com.example.services.EmailService;
import com.example.services.ServiceImpl;


@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	private EmailService service;
	Otp otp_obj;
	
	Logger logger=LoggerFactory.getLogger(Controller.class);
	
	@PostMapping(value="/getOtp")
	public ModelAndView getOtp(@RequestParam("email") String email)
	{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("email",email);
		modelAndView.setViewName("validateotp");
		int otp=service.generateOtp(email);
		logger.info("Otp has been requested");
		return modelAndView;	
	}
	
	@RequestMapping(value="/")
	public String home()
	{
		return "home";	
	}
	
	@PostMapping("/validateotp")
	public String validate(@RequestParam("otp") String otp,@RequestParam("email") String email)
	{
		logger.info("Otp validation is being processed"); 
		return service.validateOtp(otp,email);	
	}
	

}
