package com.hr.hrserver.filter;


import com.hr.hrserver.util.HttpUtils;
import com.hr.hrserver.util.SSOutil;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Component
public class LoginInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        if(isLogin != null && isLogin) {
            return true;
        }
        String token = request.getParameter("token");
        System.out.println("token=>" + token);
        if(!StringUtils.isEmpty(token)) {
            System.out.println("find token:" + token);
            String httpUrl = SSOutil.SERVER_URL_PREFIX + "/verify";
            HashMap<String, String> params = new HashMap<>();
            params.put("token", token);
            try{
                String isVerify = HttpUtils.sendHttpRequest(httpUrl, params);
                if("true".equals(isVerify)) {
                    System.out.println("verify success");
                    session.setAttribute("isLogin", true);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        SSOutil.redirectToSSOURL(request,response);
        response.sendRedirect("http://localhost:4200/login?redirectUrl="+request.getRequestURL());
        return false;


    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}


