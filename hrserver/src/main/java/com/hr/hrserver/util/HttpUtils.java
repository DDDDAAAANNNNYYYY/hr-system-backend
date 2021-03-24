package com.hr.hrserver.util;

import org.springframework.util.StreamUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

public class HttpUtils {
    public static String sendHttpRequest(String httpURL, Map<String, String> params) throws Exception {
        URL url = new URL(httpURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        if(params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<String, String> entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            connection.getOutputStream().write(sb.substring(1).toString().getBytes("UTF-8"));
        }
        connection.connect();
        //8. 接收对方响应的信息,可以使用Spring的 StreamUtils 工具类
        String response = StreamUtils.copyToString(connection.getInputStream(), Charset.forName("UTF-8"));
        return response;

    }
}
