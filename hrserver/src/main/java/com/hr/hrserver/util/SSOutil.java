package com.hr.hrserver.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class SSOutil {
    private static Properties ssoProperties = new Properties();
    public static String SERVER_URL_PREFIX;
    public static String CLIENT_HOST_URL;
    static {

        SERVER_URL_PREFIX = "http://localhost:8085";
        CLIENT_HOST_URL="http://localhost:8081";

    }
    //when filtered, redirecturl is need for redirect back after loggin
    public static String getRedirectUrl(HttpServletRequest request) {
        return CLIENT_HOST_URL + request.getServletPath();
    }
    //
    public static void redirectToSSOURL(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUrl = getRedirectUrl(request);
        StringBuilder sb = new StringBuilder().append(SERVER_URL_PREFIX)
                .append("/checkLogin?redirectUrl=")
                .append(redirectUrl);
        response.sendRedirect(sb.toString());
    }
}
