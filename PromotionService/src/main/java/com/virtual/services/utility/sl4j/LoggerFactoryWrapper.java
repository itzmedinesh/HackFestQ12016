package com.virtual.services.utility.sl4j;

import org.slf4j.LoggerFactory;

@SuppressWarnings("rawtypes")
public class LoggerFactoryWrapper {

	public static LoggerWrapper getLogger(String name) {
		return new LoggerWrapper(LoggerFactory.getLogger(name));
	}

	public static LoggerWrapper getLogger(Class clazz) {
		return getLogger(clazz.getName());
	}
}
