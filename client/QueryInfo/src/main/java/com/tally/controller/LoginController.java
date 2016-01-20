package com.tally.controller;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
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
		//String username = mapobject.get("name");
		//String passwd = mapobject.get("passwd");
		System.out.println(username);
		System.out.println(passwd);
		JSONObject jsonObject = new JSONObject();
		
		Map<String,String> result = loginservice.validateloginuser(username, passwd);
		if(result != null){
			jsonObject.append("state", "success");
		}
    	return jsonObject.toString();
	}
}