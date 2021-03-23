package com.hr.hrserver.filter;

import com.hr.hrserver.constant.AuthConst;
import com.hr.hrserver.service.SessionStorage;
import com.hr.hrserver.util.CookieUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "commonDataFilter", urlPatterns = "/pages/*")
@Component
public class LoginFilter implements Filter {
    private FilterConfig config;

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

        // already login
        if (session.getAttribute(AuthConst.IS_LOGIN) != null) {
            chain.doFilter(req, res);
            return;
        }
        System.out.println(AuthConst.IS_LOGIN);


        String token = request.getParameter(AuthConst.TOKEN);

        System.out.println("token" + token);
        if (token != null) {
            session.setAttribute(AuthConst.IS_LOGIN, true);
            session.setAttribute(AuthConst.TOKEN, token);
            // remove token from session
//
//
//            SessionStorage.INSTANCE.set(token, session);
            chain.doFilter(req, res);
            return;
        }

        // redirect to login page
        response.sendRedirect("http://localhost:8085/login.html?redirct="+request.getRequestURL());

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }
}
