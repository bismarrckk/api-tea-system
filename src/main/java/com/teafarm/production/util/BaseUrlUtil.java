package com.teafarm.production.util;

import javax.servlet.http.HttpServletRequest;

public class BaseUrlUtil {
	 public static String getSiteURL(HttpServletRequest request) {
	        String siteURL = request.getRequestURL().toString();
	        return siteURL.replace(request.getServletPath(), "");
	    }

}
