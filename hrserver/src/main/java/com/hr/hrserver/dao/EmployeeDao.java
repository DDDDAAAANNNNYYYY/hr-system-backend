package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Employee;

public interface EmployeeDao {
    public Employee getEmployeeByUserId(int id);
}
