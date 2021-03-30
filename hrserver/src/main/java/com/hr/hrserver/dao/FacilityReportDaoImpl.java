package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Facility;
import com.hr.hrserver.pojo.FacilityReport;
import com.hr.hrserver.pojo.FacilityReportDetail;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

public class FacilityReportDaoImpl extends BaseDaoImpl {
    public FacilityReportDaoImpl() {
        super(FacilityReport.class);
    }


    public List<FacilityReport> getFacilityReportByEmployeeID(int eid) {
        Query query = getCurrentSession().createQuery("from FacilityReport f where f.EmployeeID=:uid");
        query.setParameter("uid", eid);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return query.list();
    }


    public List<FacilityReportDetail> getFacilityReportDetailByFacilityReportID(int fReportId) {
        Query query = getCurrentSession().createQuery("from FacilityReportDetail f where f.ReportID=:fReportId");
        query.setParameter("fReportId", fReportId);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return query.list();
    }


    public Date getReportDateByID(int fReportId) {
        Date reportDate = ((FacilityReport) this.get(fReportId)).getReportDate();
        return reportDate;
    }
    public List<FacilityReport> getFacilityReportByListOfEmployeeID(List<Integer> eIDList){
        Query query = getCurrentSession().createQuery("from FacilityReport f where f.EmployeeID in :eIDList");
        query.setParameter("eIDList", eIDList);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return query.list();
    }
}