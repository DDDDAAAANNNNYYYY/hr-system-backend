package com.hr.hrserver.dao;
import com.hr.hrserver.pojo.User;
import com.hr.hrserver.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.rmi.server.UnicastServerRef;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao  {

    public User findUserbyUsernameAndPassword(String username, String password);



}
