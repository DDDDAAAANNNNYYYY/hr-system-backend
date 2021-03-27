package com.hr.hrserver.test;

import com.hr.hrserver.pojo.Document;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DocumentsDaoAuthAuth extends BaseDaoAuthImpl {

    public DocumentsDaoAuthAuth() {
        super(Document.class);
    }

    public List<String> getDocumentListbyEmployeeId(int id) {
        Query query = getCurrentSession().createQuery("from Document d where d.EmployeeID=:eid");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("eid", id);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        List<Document> documentList = query.list();
        List<String> path = new ArrayList<>();
        for(int i = 0; i < documentList.size(); i++) {
            path.add(documentList.get(i).getPath());
        }
        tx.commit();
        return  path ;

    }
}
