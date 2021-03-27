package com.hr.hrserver.test;

import com.hr.hrserver.pojo.Employee;

public interface EmployeeDaoAuth {
    public Employee getEmployeeByUserIdAuth(int id);
}
