package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Address;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

public class AddressDaoImpl extends BaseDaoImpl {
    public AddressDaoImpl() { super(Address.class); }


    public Address getAddressByEmployeeID(int eid) {
        Query query = getCurrentSession().createQuery("from Address a where a.ID=:id");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("id", eid);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        Address a = (Address) query.list().get(0);
        return  a ;
    }
}
