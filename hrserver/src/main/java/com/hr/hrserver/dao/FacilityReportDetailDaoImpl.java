package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.FacilityReportDetail;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class FacilityReportDetailDaoImpl extends BaseDaoImpl {

    public FacilityReportDetailDaoImpl() {
        super(FacilityReportDetail.class);
    }

    public List<FacilityReportDetail> getFacilityReportDetailByFacilityReportID(int FRid){
        Query query = getCurrentSession().createQuery("from FacilityReportDetail f where f.ReportID=:FRid");
        query.setParameter("FRid", FRid);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return query.list();
    }
}
