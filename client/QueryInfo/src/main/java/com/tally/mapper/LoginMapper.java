package com.tally.mapper;

import java.util.Map;

public interface LoginMapper {
	public Map<String, String>validateloginuser(String username,String passwd);
}
