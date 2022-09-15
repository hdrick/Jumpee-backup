package com.dricks.jumpee.helpers;

import java.util.HashMap;
import java.util.Map;

public class UnauthorizedUser {
	
    public static Object getMessage(String path) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("path", path);
    	map.put("error", "Unauthorized");
    	map.put("message", "Login is required to access this resource");
    	map.put("status", 401);
    	return map;
    }
    
    public static Object redirectToLogin(String path) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("path", path);
    	map.put("error", "Bad Request");
    	map.put("message", "Login is required: http://localhost:9090/login");
    	map.put("status", 400);
    	return map;
    }
    
}


