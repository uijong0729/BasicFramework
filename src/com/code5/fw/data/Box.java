package com.code5.fw.data;

import java.io.Serializable;

public abstract class Box implements Serializable{

	private static final long serialVersionUID = 1595969490528687187L;
	public abstract Object get(String key); 
	public abstract void put(String key, Object obj);
	public String getString(String key) {
		Object s = this.get(key);
		if (s == null || (s instanceof String) == false) {
			return "";
		}else {
			return (String) s;
		}
	}
}
