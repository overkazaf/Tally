package com.tally.controller;

import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tally.service.LoginService;


//http://localhost:8080/tally/login?username=panfei&passwd=123456
@Controller
public class LoginController {
	@Autowired private LoginService loginservice;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
    @ResponseBody public String login(String username,String passwd) throws JSONException{
		Map<String,String> result = loginservice.validateloginuser(username, passwd);
		String resStr = "";
		if(result != null){
			resStr = "{'status':'success'}";
		}
    	return resStr;
	}
}