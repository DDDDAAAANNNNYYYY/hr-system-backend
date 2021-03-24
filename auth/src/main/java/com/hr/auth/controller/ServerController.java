package com.hr.auth.controller;

import com.hr.auth.db.MockDB;
import com.hr.auth.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ServerController {
    private static final String signingKey = "signingKey";
    //this is login page--front end
    @RequestMapping("/index")
    public String index() {
        return "login.jsp";
    }

    @RequestMapping("/login")
    public String login(String username, String password, String redirectUrl,
                        HttpSession session, Model model) {
        System.out.println("username=>" + username + " password=>" + password + " redirecturl=>"
        +redirectUrl);
        //should check from db
        if("admin@gmail.com".equals(username) && "123456".equals(password)) {
            //login in successfully at first time
            String token = JwtUtil.generateToken(signingKey, username);
            System.out.println("token=>" + token);
            session.setAttribute("token", token);
            MockDB.T_TOKEN.add(token);
            model.addAttribute("token", token);
            return "redirect:" + redirectUrl + "?token=" + token;// after login redirect to the page where it comes from
        }
        System.out.println("wrong username/password");
        model.addAttribute("redirectUrl", redirectUrl); // never lost the url where it comes from
        return "login.jsp";
    }
    //other pages that is filtered, all will be redirect here to check if the user is logged in or not
    @RequestMapping("/checkLogin")
    public String checkLogin(String redirectUrl, HttpSession session, Model model) {
        //whether we have token in session
        String token = (String) session.getAttribute("token");
        if(StringUtils.isEmpty(token)) {
            System.out.println("token is empty, redirect to login page");
            //if no session, needs login, so redirect to login page and never forget the url where it comes from
            model.addAttribute("redirectUrl", redirectUrl);
            return "login";
        } else {
            //has a global session, then return it to client side
            model.addAttribute("token", token);
            return "redirect:" + redirectUrl;
        }
    }
    @RequestMapping("/verify")
    @ResponseBody
    public String verifyToken(String token, String clientUrl,String jessionid) {
        if(MockDB.T_TOKEN.contains(token)) {
            System.out.println("successfully verify token: " + token);
            return "true";
        }
        return "false";
    }

}
