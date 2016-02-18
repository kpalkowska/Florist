package com.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class LogServiceImpl implements LogService {

	private static Logger LOG = LoggerFactory.getLogger(LogServiceImpl.class);
	
	@Override
	public void logInfo(String msg) {
		if (LOG.isInfoEnabled()) {
			LOG.info(msg);
		}
	}

	@Override
	public void logError(String msg, Throwable t) {
		if (LOG.isErrorEnabled()) {
			LOG.error(msg);
		}
	}
}
