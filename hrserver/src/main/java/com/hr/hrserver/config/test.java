package com.hr.hrserver.config;

import com.hr.hrserver.dao.EmployeeDao;
import com.hr.hrserver.dao.EmployeeDaoImpl;
import com.hr.hrserver.dao.UserDao;
import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.User;
import com.hr.hrserver.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.Connection;
import java.sql.SQLException;

public class test {

    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    @Test
    public void test() {
        Employee e = employeeDao.getEmployeeByUserId(1);
        System.out.println(e);
    }

}
