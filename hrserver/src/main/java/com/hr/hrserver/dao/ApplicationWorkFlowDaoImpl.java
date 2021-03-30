package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.ApplicationWorkFlow;
import com.hr.hrserver.pojo.Employee;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ApplicationWorkFlowDaoImpl extends BaseDaoImpl {
    public ApplicationWorkFlowDaoImpl(){super(ApplicationWorkFlow.class);}


    public ApplicationWorkFlow getAWFbyEmployeeID(int id) {
        Query query = getCurrentSession().createQuery("from ApplicationWorkFlow a where a.EmployeeID=:uid");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("uid", id);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        ApplicationWorkFlow a = (ApplicationWorkFlow)query.list().get(0);
        return  a;
    }

    public List<Integer> getEidByAWFStatus(String status){
        Query query = getCurrentSession().createQuery(
                "select EmployeeID from ApplicationWorkFlow where Status=:status");
        query.setParameter("status", status);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return query.list();
    }
}
