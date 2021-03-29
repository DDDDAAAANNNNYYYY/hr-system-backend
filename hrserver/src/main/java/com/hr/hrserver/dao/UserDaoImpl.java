package com.hr.hrserver.dao;

import com.hr.hrserver.config.HibernateConfig;
import com.hr.hrserver.pojo.User;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

@Service
public class UserDaoImpl extends BaseDaoImpl implements UserDao{

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findUserbyUsernameAndPassword(String username, String password) {
        Query query = getCurrentSession().createQuery("from User u where u.username=:uname and u.password=:pword");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("uname", username);
        query.setParameter("pword",password);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        User u = (User)query.list().get(0);
        tx.commit();
        return  u ;
    }
    public int findIdbyNmae(String username) {
        Query query = getCurrentSession().createQuery("from User u where u.username=:uname ");
//        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("uname", username);

        if(CollectionUtils.isEmpty(query.list())) {
            return -1;
        }
        User u = (User)query.list().get(0);
//        tx.commit();
        return  u.getId() ;
    }
    public int findIdbyEmail(String email) {
        Query query = getCurrentSession().createQuery("from User u where u.email=:uname ");
//        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("uname", email);

        if(CollectionUtils.isEmpty(query.list())) {
            return -1;
        }
        User u = (User)query.list().get(0);
//        tx.commit();
        return  u.getId() ;
    }
    public String findEmailbyNmae(String username) {
        Query query = getCurrentSession().createQuery("from User u where u.username=:uname ");
//        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("uname", username);

        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        User u = (User)query.list().get(0);
//        tx.commit();
        return  u.getEmail() ;
    }
}
