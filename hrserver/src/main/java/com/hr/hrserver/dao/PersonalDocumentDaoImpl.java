package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Document;
import com.hr.hrserver.pojo.PersonalDocument;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class PersonalDocumentDaoImpl extends BaseDaoImpl implements PersonalDocumentDao{
    public PersonalDocumentDaoImpl() {
        super(PersonalDocument.class);
    }

    @Override
    public List<PersonalDocument> getPersonalDocumentByEmployeeID(int eid) {
        Query query = getCurrentSession().createQuery("from PersonalDocument pd where pd.EmployeeID=:uid");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("uid", eid);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return query.list();
    }

    @Override
    public String getNewestDocumentPathByPersonalIDAndTitle(int eid, String title) {
        Query query = getCurrentSession().createQuery("from PersonalDocument pd where pd.EmployeeID=:eid and pd.Title=:title order by CreatedDate");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("eid", eid);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return (String) query.list().get(0);
    }

    public String getNewestDocPathByEIDAndComment(int eid, String comment) {
        Query query = getCurrentSession().createQuery(
                "from PersonalDocument pd where pd.EmployeeID=:eid and pd.Comment=:comment order by CreatedDate"
        );
        query.setParameter("eid", eid);
        query.setParameter("comment", comment);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return ((PersonalDocument) query.list().get(0)).getPath();
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

