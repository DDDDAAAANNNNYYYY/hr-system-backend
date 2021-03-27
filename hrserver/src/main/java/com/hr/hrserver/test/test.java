package com.hr.hrserver.test;

import com.hr.hrserver.dao.EmployeeDaoImpl;
import com.hr.hrserver.pojo.Employee;
import org.junit.Test;

public class test {

    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    @Test
    public void test() {
        Employee e = employeeDao.getEmployeeByUserId(1);
        System.out.println(e);
    }

}
