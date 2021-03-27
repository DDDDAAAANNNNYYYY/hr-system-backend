package com.hr.auth.dao.base;

import com.hr.hrserver.dao.BaseDaoImpl;

import com.hr.hrserver.pojo.VisaStatus;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class VisaStatusDao extends BaseDaoImpl {
    public VisaStatusDao() {
        super(VisaStatus.class);
    }


}
