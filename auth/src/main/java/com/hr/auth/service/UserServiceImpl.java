package com.hr.auth.service;

import com.hr.hrserver.pojo.User;

import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl implements UserService {

    public User find(User user) {
        //a,b,c  => user d,ef=> emply
        User u = new User();

        return u;
    }
}