package com.hr.auth.dao.base;

import com.hr.hrserver.dao.BaseDaoImpl;

import com.hr.hrserver.pojo.VisaStatus;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class VisaStatusDao extends BaseDaoImpl {
    public VisaStatusDao() {
        super(VisaStatus.class);
    }

    public List<VisaStatus> getVisaById(int id){
        Query query = getCurrentSession().createQuery("from VisaStatus c where c.EmployeeID=:eid");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("eid", id);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        List<VisaStatus> visaList = query.list();
        tx.commit();
        return visaList;
    }
}
