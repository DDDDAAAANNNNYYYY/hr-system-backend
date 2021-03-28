package com.hr.hrserver.service;

import com.hr.hrserver.dao.EmployeeDaoImpl;
import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.dao.VisaStatusDao;
import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.VisaStatus;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service
public class VisaService {

    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();
    VisaStatusDao visaStatusDao = new VisaStatusDao();

    public VisaStatus getVisaStatusByName(String username){
        int id = userDao.findIdbyNmae(username);
        Employee e = employeeDao.getEmployeeByUserId(id);
        int eid = e.getID();
        VisaStatus v = visaStatusDao.getVisaStatusByEmployeId(eid);
        return v;
    }
    public void updateVisaStatud(VisaStatus v){
        visaStatusDao.saveOrupdate(v);
    }

}
