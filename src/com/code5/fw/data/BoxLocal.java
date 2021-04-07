package com.code5.fw.data;

import java.util.*;

public class BoxLocal extends Box{
	private static final long serialVersionUID = 8918495845153476972L;

	private Map<String, Object> map = new HashMap<String, Object>();
	
	@Override
	public Object get(String key) {
		return map.get(key);
	}

	@Override
	public void put(String key, Object obj) {
		map.put(key, obj);		
	}
	

}
