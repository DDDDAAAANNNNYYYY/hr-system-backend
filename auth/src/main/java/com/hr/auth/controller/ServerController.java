package com.hr.auth.controller;

import com.hr.auth.db.MockDB;
import com.hr.auth.util.JwtUtil;
import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin
@RestController
public class ServerController {
    private static final String signingKey = "signingKey";
    UserDaoImpl userDao = new UserDaoImpl();
    //this is login page--front end
    @RequestMapping("/index")
    public String index() {
        return "login.jsp";
    }
//    @CrossOrigin
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user,HttpSession session) {
        System.out.println("username=>" + user.get("username") + " password=>" + user.get("password" ));
        //should check from db
        User u = userDao.findUserbyUsernameAndPassword(user.get("username"), user.get("password"));
        if(u != null) {
            //login in successfully at first time
            String token = JwtUtil.generateToken(signingKey, user.get("username" ));
            System.out.println("token=>" + token);
            session.setAttribute("token", token);
            MockDB.T_TOKEN.add(token);
//            model.addAttribute("token", token);
            Map<String, String> ans = new HashMap<>();
            ans.put("token",token);
            return token;
//            return "redirect:" + redirectUrl + "?token=" + token;// after login redirect to the page where it comes from
        }
        System.out.println("wrong username/password");
//        model.addAttribute("redirectUrl", redirectUrl); // never lost the url where it comes from
        return "";
//        return "login.jsp";
//        return "redirect:http://localhost:4200?redirectUrl="+redirectUrl;
    }
    //other pages that is filtered, all will be redirect here to check if the user is logged in or not
    @PostMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(@RequestBody String redirectUrl, HttpSession session, Model model) {
        //whether we have token in session
        String token = (String) session.getAttribute("token");

        if(StringUtils.isEmpty(token) || !MockDB.T_TOKEN.contains(token)) {
            System.out.println("token is empty, redirect to login page");
            //if no session, needs login, so redirect to login page and never forget the url where it comes from
            model.addAttribute("redirectUrl", redirectUrl);
            System.out.println("data get:" + redirectUrl);
//            return "login.jsp";
            return "redirect:http://localhost:4200/login?redirect=" + redirectUrl;
        } else {
            //has a global session, then return it to client side
            model.addAttribute("token", token);
            //return personinfo
            //model-perosn  personalInfo
            System.out.println("we got token");
            System.out.println("redre" + redirectUrl);
            return "success";
        }
    }
    @RequestMapping("/verify")
    @ResponseBody
    public String verifyToken(String token) {
        if(MockDB.T_TOKEN.contains(token)) {
            System.out.println("successfully verify token: " + token);
            return "true";
        }
        return "false";
    }

}
