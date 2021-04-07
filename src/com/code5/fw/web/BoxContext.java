package com.code5.fw.web;

import com.code5.fw.data.Box;
import com.code5.fw.data.BoxLocal;

/**
 * @author kroch
 *
 */
public class BoxContext {
	private static ThreadLocal<Box> threadLocal = new ThreadLocal<>();
	
	/**
	 * @return
	 */
	public static Box getThread() {
		Box box = threadLocal.get();
		if (box != null) {
			return box;
		}else {
			box = new BoxLocal();
			setThread(box);
			return box;
		}
	}
	
	/**
	 * @param box
	 */
	static void setThread(Box box) {
		threadLocal.set(box);
	}
	
	/**
	 * 
	 */
	static void removeThread() {
		threadLocal.remove();
	}
}
