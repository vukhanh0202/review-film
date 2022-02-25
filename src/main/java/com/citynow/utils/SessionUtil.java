package com.citynow.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Manage Session Utility
 * @author VuKhanh
 */
public class SessionUtil {

    private static SessionUtil sessionUtil = null;

    /**
     * Singleton Design pattern
     * @return SessionUtil
     */
    public static SessionUtil getInstance() {
        if (sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }

    /**
     * Put value to session
     * @param request
     * @param key
     * @param value
     */
    public void putValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    /**
     * Get value from session
     * @param request
     * @param key
     * @return
     */
    public Object getValue(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    /**
     * Remove value from session
     * @param request
     * @param key
     */
    public void removeValue(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
}
