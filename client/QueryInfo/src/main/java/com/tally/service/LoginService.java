package com.tally.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tally.mapper.LoginMapper;

@Service
public class LoginService {
	@Autowired LoginMapper loginMapper;
	public Map<String, String> validateloginuser(String username, String passwd) {
		try {
		  Map<String, String> results = loginMapper.validateloginuser(username,passwd);
		  return results;
		} catch (Exception e) {
			return null;
		}
	}
}
