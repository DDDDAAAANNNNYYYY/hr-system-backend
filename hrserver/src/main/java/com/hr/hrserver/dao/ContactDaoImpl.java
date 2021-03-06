package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Contact;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ContactDaoImpl extends BaseDaoImpl implements ContactDao{
    public ContactDaoImpl() {super(Contact.class);}

    @Override
    public List<Contact> getAllContactByEmployeeID(int eid) {
        Query query = getCurrentSession().createQuery("from Contact c where c.EmployeeID=:uid");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("uid", eid);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return query.list();
    }

    public Contact getReferenceByEid(int eid){
        Query query = getCurrentSession().createQuery("from Contact c where c.EmployeeID=:uid and isReference=1");
        query.setParameter("uid", eid);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return (Contact) query.list().get(0);
    }

    public List<Contact> getEmergContactByEid(int eid){
        Query query = getCurrentSession().createQuery("from Contact c where c.EmployeeID=:uid and isEmergency=1");
        query.setParameter("uid", eid);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return query.list();
    }


}
