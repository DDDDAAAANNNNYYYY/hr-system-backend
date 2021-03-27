package com.hr.auth.dao.base;

import com.hr.auth.dao.BaseDaoAuthImpl;

import com.hr.hrserver.pojo.Document;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


public class DocumentsDaoAuthAuth extends BaseDaoAuthImpl {

    public DocumentsDaoAuthAuth() {
        super(Document.class);
    }

    public List<Document> getDocumentListbyEmployeeId(int id) {
        Query query = getCurrentSession().createQuery("from Document d where d.EmployeeID=:eid");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("eid", id);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        List<Document> documentList = query.list();

        List<String> path = new ArrayList<>();

        tx.commit();
        return  documentList ;

    }
}
