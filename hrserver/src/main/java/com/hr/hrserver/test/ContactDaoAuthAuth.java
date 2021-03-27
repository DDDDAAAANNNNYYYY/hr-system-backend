package com.hr.hrserver.test;

import com.hr.hrserver.pojo.Contact;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class ContactDaoAuthAuth extends BaseDaoAuthImpl {
    public ContactDaoAuthAuth(){
        super(Contact.class);
    }
    public List<Contact> getContactListbyEmployeeId(int id) {
        Query query = getCurrentSession().createQuery("from Contact c where c.EmployeeID=:eid");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("eid", id);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        List<Contact> contactList = query.list();
        tx.commit();
        return  contactList ;

    }
}
