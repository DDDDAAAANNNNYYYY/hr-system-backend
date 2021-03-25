package com.hr.hrserver.dao;

import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<E, I extends Serializable> {
    I save(E e);

    void saveOrupdate(E e);

    void delete(E e);

    E get(I i);


    void saveAll(List<E> e);
}
