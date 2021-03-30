package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.RegistrationToken;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
@Repository
public class registrationTokenDaoImpl extends BaseDaoImpl implements registrationTokenDao{
    public registrationTokenDaoImpl() {
        super(RegistrationToken.class);
    }

    @Override
    public boolean isValidToken(String token) {
        Transaction tx = getCurrentSession().beginTransaction();
        Query query = getCurrentSession().createQuery("from RegistrationToken r where r.Token=:t");
        query.setParameter("t", token);
        if(CollectionUtils.isEmpty(query.list())){
            return false;
        }
        tx.commit();
        return true;
    }
    public void saveReigistrationToken(RegistrationToken rtoken) {
        save(rtoken);
    }

}
