package com.hr.auth.dao;

import com.hr.auth.dao.BaseDaoAuth;
import com.hr.auth.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class BaseDaoAuthImpl<E, I extends Serializable> implements BaseDaoAuth<E, I > {
    private Class<E> entityClass;

    public BaseDaoAuthImpl(Class<E> entityClass){
        this.entityClass=entityClass;
    }

    @SuppressWarnings("unchecked")
    public I save(E e) {
        Transaction tx = getCurrentSession().beginTransaction();
        I id=(I) getCurrentSession().save(e);
        tx.commit();
        return id;
    }

    public void saveOrupdate(E e) {
        Transaction tx = getCurrentSession().beginTransaction();
        getCurrentSession().saveOrUpdate(e);
        tx.commit();
    }

    public void delete(E e) {
        Transaction tx = getCurrentSession().beginTransaction();
        getCurrentSession().delete(e);
        tx.commit();
    }

    @SuppressWarnings("unchecked")
    public E get(I i) {
        Transaction tx = getCurrentSession().beginTransaction();
        E e = getCurrentSession().get(entityClass, i);
        tx.commit();
        return (E) e;

    }


    public void saveAll(List<E> e)
    {
        Transaction tx = getCurrentSession().beginTransaction();
        for (E e2 : e) {
            getCurrentSession().save(e2);
        }
        tx.commit();
    }

    public Session getCurrentSession(){

        return HibernateUtil.getSession();
    }
}
