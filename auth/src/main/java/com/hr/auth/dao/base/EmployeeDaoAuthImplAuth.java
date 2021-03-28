package com.hr.auth.dao.base;

import com.hr.auth.dao.BaseDaoAuthImpl;

import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.User;
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

    public Employee getEmployeeByUserName(String uname) {
        UserDaoImpl ud = new UserDaoImpl();
        String email = (String)ud.findEmailbyNmae(uname);

        Query query = getCurrentSession().createQuery("from  Employee e  where e.Email=:email");
//        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("email", email);
//        if(CollectionUtils.isEmpty(query.list())) {
//            return null;
//        }
        Employee e = (Employee) query.list().get(0);

//        tx.commit();
//        getCurrentSession().close();
        return e;
    }
}
