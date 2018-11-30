package com.maxcar.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author junping.huang
 */
public final class EnvironmentUtil {

	private static final Logger LOG = LoggerFactory.getLogger(EnvironmentUtil.class);

	private static String SYSTEM_ENVIRONMENT = "";

	static {
		InputStream in = EnvironmentUtil.class.getClassLoader().getResourceAsStream("common.properties");
		Properties po = new Properties();
		try {
			po.load(in);
			SYSTEM_ENVIRONMENT = po.getProperty("system.environment");
			in.close();
		} catch (Exception e) {
			SYSTEM_ENVIRONMENT = "pro";
			LOG.error("not find file config.properties", e);
		}
	}

	public static boolean isProduct() {
		return "pro".equals(SYSTEM_ENVIRONMENT);
	}
}