package me.phoibe.doc.cms.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PlatLogUtil {
	/**
	 * log4J声明
	 */
	private static Log logger = LogFactory.getLog(PlatLogUtil.class);

	/**
	 * 打印控制台信息方法
	 * 
	 * @param obj
	 */
	public static void println(Object obj) {
		// System.out.println(obj);
	}

	/**
	 * 打印堆栈信息
	 * 
	 * @param e
	 */
	public static void printStackTrace(Exception e) {
		logger.error(ExceptionUtils.getStackTrace(e));
	}

	/**
	 * 
	 * @param e
	 */
	public static void doNothing(Exception e) {

	}
}
