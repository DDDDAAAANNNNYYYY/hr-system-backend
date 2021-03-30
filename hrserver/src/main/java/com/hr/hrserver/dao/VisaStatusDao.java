package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.User;
import com.hr.hrserver.pojo.VisaStatus;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class VisaStatusDao extends BaseDaoImpl{

    public VisaStatusDao() {
        super(VisaStatus.class);
    }
    public VisaStatus getVisaStatusByEmployeId(int eid){

        Query query = getCurrentSession().createQuery("from VisaStatus v where v.EmployeeID=:eid");
//        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("eid", eid);
        VisaStatus v = (VisaStatus)query.list().get(0);

//        tx.commit();

        return v;
    }

    public VisaStatus getNewestValidVisaByEid(int eid){
        Query query = getCurrentSession().createQuery(
                "from VisaStatus v where v.EmployeeID=:eid and v.Active=1 order by VisaEndDate");
        query.setParameter("eid", eid);
        return (VisaStatus) query.list().get(0);
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
