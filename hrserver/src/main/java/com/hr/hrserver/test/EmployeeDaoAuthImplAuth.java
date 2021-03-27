//package com.hr.hrserver.test;
//
//import com.hr.auth.pojo.Employee;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.CollectionUtils;
//
//@Repository
//public class EmployeeDaoAuthImplAuth extends BaseDaoAuthImpl implements EmployeeDaoAuth {
//
//    public EmployeeDaoAuthImplAuth() {
//        super(Employee.class);
//    }
//
//    @Override
//    public Employee getEmployeeByUserIdAuth(int id) {
//
//        Query query = getCurrentSession().createQuery("from  Employee where e.UserID=:uid");
//
//        Transaction tx = getCurrentSession().beginTransaction();
//        query.setParameter("uid", id);
//        if(CollectionUtils.isEmpty(query.list())) {
//            return null;
//        }
//        Employee e = (Employee)query.list().get(0);
//        tx.commit();
//        getCurrentSession().close();
//        return  e ;
//    }
//
//    public Employee getEmployeeByUserEmail(String email) {
//        Query query = getCurrentSession().createQuery("from Employee e where e.Email=:email");
//        Transaction tx = getCurrentSession().beginTransaction();
//        query.setParameter("email", email);
//        if(CollectionUtils.isEmpty(query.list())) {
//            return null;
//        }
//        Employee e = (Employee)query.list().get(0);
//        tx.commit();
//        getCurrentSession().close();
//        return  e ;
//    }
//}
