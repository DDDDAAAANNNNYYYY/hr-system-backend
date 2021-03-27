package com.hr.auth.dao.base;

import com.hr.auth.dao.BaseDaoAuthImpl;

import com.hr.hrserver.pojo.Employee;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;


public class EmployeeDaoAuthImplAuth extends BaseDaoAuthImpl {

    public EmployeeDaoAuthImplAuth() {
        super(Employee.class);
    }


    public Employee getEmployeeByUserIdAuth(int id) {

        Query query = getCurrentSession().createQuery("from Employee e where e.UserID=:uid");

        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("uid", id);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        Employee e = (Employee)query.list().get(0);
        tx.commit();

        return  e ;
    }

    public List<Employee> getEmployeeByUserEmail(String email) {
        Query query = getCurrentSession().createQuery("from Employee e where e.ID=:email");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("email", 1);
//        if(CollectionUtils.isEmpty(query.list())) {
//            return null;
//        }
        List<Employee> e = query.list();
        tx.commit();
        getCurrentSession().close();
        return  e ;
    }
}
