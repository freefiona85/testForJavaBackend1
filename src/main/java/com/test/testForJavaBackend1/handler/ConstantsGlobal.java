package com.test.testForJavaBackend1.handler;

import javax.servlet.http.HttpServletRequest;

public class ConstantsGlobal {
	public static final String OK = "1";
	public static final String ERROR = "0";
	public static final String MESSAGE_SUCCESS = "Ok";
	public static final String MESSAGE_ERROR = "Error";
	public static final Integer MAX_RELEASE = 10;
	public static final String API_KEY = "3a5f4cfd310559bb62ad74024f732e59";
	public static final String API_SECRET ="5fafaa8b04e92796";
	public static final String getBaseUrl(HttpServletRequest req) {
	    return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
	}

}
