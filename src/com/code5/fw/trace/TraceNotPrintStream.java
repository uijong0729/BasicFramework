package com.code5.fw.trace;

import java.io.PrintStream;

/**
 * @author kroch
 *
 */
class TraceNotPrintStream extends PrintStream {

	/**
	 * 
	 */
	public TraceNotPrintStream() {

		super(new TraceNotOutputStream());
	}
}
