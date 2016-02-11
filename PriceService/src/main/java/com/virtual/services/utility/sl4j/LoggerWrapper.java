package com.virtual.services.utility.sl4j;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class LoggerWrapper implements Logger {

	protected final Logger logger;

	public LoggerWrapper(Logger logger) {
		this.logger = logger;
	}

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public boolean isTraceEnabled(Marker marker) {
		return logger.isTraceEnabled(marker);
	}

	public void trace(String msg) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(msg);
		}
	}

	public void trace(String format, Object arg) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(format, arg);
		}
	}

	public void trace(String format, Object arg1, Object arg2) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(format, arg1, arg2);
		}
	}

	public void trace(String format, Object[] argArray) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(format, argArray);
		}
	}

	public void trace(String msg, Throwable t) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(msg, t);
		}
	}

	public void trace(Marker marker, String msg) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(marker, msg);
		}
	}

	public void trace(Marker marker, String format, Object arg) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(marker, format, arg);
		}
	}

	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(marker, format, arg1, arg2);
		}
	}

	public void trace(Marker marker, String format, Object[] argArray) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(marker, format, argArray);
		}
	}

	public void trace(Marker marker, String msg, Throwable t) {
		if (!logger.isTraceEnabled()) {
			return;
		} else {
			logger.trace(marker, msg, t);
		}
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isDebugEnabled(Marker marker) {
		return logger.isDebugEnabled(marker);
	}

	public void debug(String msg) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(msg);
		}
	}

	public void debug(String format, Object arg) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(format, arg);
		}
	}

	public void debug(String format, Object arg1, Object arg2) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(format, arg1, arg2);
		}
	}

	public void debug(String format, Object[] argArray) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(format, argArray);
		}
	}

	public void debug(String msg, Throwable t) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(msg, t);
		}
	}

	public void debug(Marker marker, String msg) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(marker, msg);
		}
	}

	public void debug(Marker marker, String format, Object arg) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(marker, format, arg);
		}
	}

	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(marker, format, arg1, arg2);
		}
	}

	public void debug(Marker marker, String format, Object[] argArray) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(marker, format, argArray);
		}
	}

	public void debug(Marker marker, String msg, Throwable t) {
		if (!logger.isDebugEnabled()) {
			return;
		} else {
			logger.debug(marker, msg, t);
		}
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isInfoEnabled(Marker marker) {
		return logger.isInfoEnabled(marker);
	}

	public void info(String msg) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(msg);
		}
	}

	public void info(String format, Object arg) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(format, arg);
		}
	}

	public void info(String format, Object arg1, Object arg2) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(format, arg1, arg2);
		}
	}

	public void info(String format, Object[] argArray) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(format, argArray);
		}
	}

	public void info(String msg, Throwable t) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(msg, t);
		}
	}

	public void info(Marker marker, String msg) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(marker, msg);
		}
	}

	public void info(Marker marker, String format, Object arg) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(marker, format, arg);
		}
	}

	public void info(Marker marker, String format, Object arg1, Object arg2) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(marker, format, arg1, arg2);
		}
	}

	public void info(Marker marker, String format, Object[] argArray) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(marker, format, argArray);
		}
	}

	public void info(Marker marker, String msg, Throwable t) {
		if (!logger.isInfoEnabled()) {
			return;
		} else {
			logger.info(marker, msg, t);
		}
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public boolean isWarnEnabled(Marker marker) {
		return logger.isWarnEnabled(marker);
	}

	public void warn(String msg) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(msg);
		}
	}

	public void warn(String format, Object arg) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(format, arg);
		}
	}

	public void warn(String format, Object arg1, Object arg2) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(format, arg1, arg2);
		}
	}

	public void warn(String format, Object[] argArray) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(format, argArray);
		}
	}

	public void warn(String msg, Throwable t) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(msg, t);
		}
	}

	public void warn(Marker marker, String msg) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(marker, msg);
		}
	}

	public void warn(Marker marker, String format, Object arg) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(marker, format, arg);
		}
	}

	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(marker, format, arg1, arg2);
		}
	}

	public void warn(Marker marker, String format, Object[] argArray) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(marker, format, argArray);
		}
	}

	public void warn(Marker marker, String msg, Throwable t) {
		if (!logger.isWarnEnabled()) {
			return;
		} else {
			logger.warn(marker, msg, t);
		}
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isErrorEnabled(Marker marker) {
		return logger.isErrorEnabled(marker);
	}

	public void error(String msg) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(msg);
		}
	}

	public void error(String format, Object arg) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(format, arg);
		}
	}

	public void error(String format, Object arg1, Object arg2) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(format, arg1, arg2);
		}
	}

	public void error(String format, Object[] argArray) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(format, argArray);
		}
	}

	public void error(String msg, Throwable t) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(msg, t);
		}
	}

	public void error(Marker marker, String msg) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(marker, msg);
		}
	}

	public void error(Marker marker, String format, Object arg) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(marker, format, arg);
		}
	}

	public void error(Marker marker, String format, Object arg1, Object arg2) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(marker, format, arg1, arg2);
		}
	}

	public void error(Marker marker, String format, Object[] argArray) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(marker, format, argArray);
		}
	}

	public void error(Marker marker, String msg, Throwable t) {
		if (!logger.isErrorEnabled()) {
			return;
		} else {
			logger.error(marker, msg, t);
		}
	}

	public String getName() {
		return logger.getName();
	}
}
