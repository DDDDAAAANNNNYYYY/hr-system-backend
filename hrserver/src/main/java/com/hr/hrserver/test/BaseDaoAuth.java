package com.hr.hrserver.test;

import java.io.Serializable;
import java.util.List;

public interface BaseDaoAuth<E, I extends Serializable> {
    I save(E e);

    void saveOrupdate(E e);

    void delete(E e);

    E get(I i);


    void saveAll(List<E> e);
}
