package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.User;
import com.hr.hrserver.pojo.VisaStatus;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class VisaStatusDao extends BaseDaoImpl{
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();
    public VisaStatusDao() {
        super(VisaStatus.class);
    }
    public VisaStatus getVisaStatusByUsername(String username){
        int id = userDao.findIdbyNmae(username);
        Employee e = employeeDao.getEmployeeByUserId(id);
        int eid = e.getID();
        Query query = getCurrentSession().createQuery("from VisaStatus v where v.EmployeeID=:eid");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("eid", eid);
        VisaStatus v = (VisaStatus)query.list().get(0);

        tx.commit();

        return v;
    }
}
