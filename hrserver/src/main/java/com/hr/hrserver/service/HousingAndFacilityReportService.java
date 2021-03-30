package com.hr.hrserver.service;

import com.hr.hrserver.dao.*;
import com.hr.hrserver.model.FacilityReportDetailAndUsername;
import com.hr.hrserver.pojo.*;
import com.hr.hrserver.util.UtilFunction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
class FacilityReportForm{
    int employeeID;
    String title;
    String description;
}
@Getter
@Setter
@NoArgsConstructor
class FacilityReportDetailForm{
    int reportID;
    int employeeID;
    String Comment;
}

@Service
public class HousingAndFacilityReportService {


    public int addNewReport(Map<String, String> fRInput){

        //add to form FacilityReport
        FacilityReport facilityReport = new FacilityReport();
        facilityReport.setTitle(fRInput.get("title"));
        //get employeeID by username
        UserDaoImpl userDao = new UserDaoImpl();
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        int eID = employeeDao.getEmployeeByUserId(
                userDao.findIdbyNmae( fRInput.get("username"))
        ).getID();
        facilityReport.setEmployeeID(eID);
        facilityReport.setReportDate(UtilFunction.getTodayDate());
        facilityReport.setDescription( fRInput.get("description"));
        facilityReport.setStatus("Open");

        FacilityReportDaoImpl fRDI = new FacilityReportDaoImpl();
        int reportID = (int) fRDI.save(facilityReport);

        return reportID;
        //add to form FacilityReportDetail
    }

    public int addNewCommentToReport(Map<String, String> fRDInput){
        //FacilityReportDetailForm fRDF = (FacilityReportDetailForm) FacilityReportDetailInput;

        //find report create date
        FacilityReportDaoImpl fRDI = new FacilityReportDaoImpl();
        Date reportDate = fRDI.getReportDateByID(Integer.parseInt( fRDInput.get("reportID")));

        //assemble object and save to table FacilityReportDetail
        FacilityReportDetail fRDObject = new FacilityReportDetail();
        fRDObject.setReportID(Integer.parseInt( fRDInput.get("reportID")));

        ////get employeeID by username
        UserDaoImpl userDao = new UserDaoImpl();
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        int eID = employeeDao.getEmployeeByUserId(
                userDao.findIdbyNmae((String) fRDInput.get("username"))
        ).getID();
        fRDObject.setEmployeeID(eID);
        fRDObject.setComment((String) fRDInput.get("comment"));
        fRDObject.setCreatedDate(reportDate);
        fRDObject.setLastModificationDate(UtilFunction.getTodayDate());

        FacilityReportDetailDaoImpl fRDDI = new FacilityReportDetailDaoImpl();
        return (int) fRDDI.save(fRDObject);
    }

    public HouseDetail getHouseDetailByEmployeeID(int eID){
        HouseDetail houseDetail = new HouseDetail();

        //get HouseID from table Employee using eID
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        Employee employee = (Employee) employeeDao.get(eID);
        int houseID = employee.getHouseID();

        //get Address column (which refers to Address.ID) from House using House.ID
        //And set Address Object to houseDetail
        HouseDaoImpl houseDao = new HouseDaoImpl();
        House house = houseDao.getHouseByID(houseID);
        AddressDaoImpl addressDao = new AddressDaoImpl();
        //assign to houseDetail
        houseDetail.address=
                (Address) addressDao.get(
                        house.getAddressID()
                );

        //in Employee table find all column with this same HouseID,
        // retrieve their FirstName,PreferredName, cellPhone as a list
        List<OccupantRaw> returnList = employeeDao.getSomeInfoByHouseID(houseID);
        System.out.println(returnList.get(0).getClass());
        List<Occupant> oList = new ArrayList<>();
        for(OccupantRaw o : returnList){
            Occupant newOccu = new Occupant();
            newOccu.name = o.PreferredName==""? o.FirstName : o.PreferredName;
            newOccu.phoneNumber = o.CellPhone;
            oList.add(newOccu);
        }
        //assign to houseDetail
        houseDetail.occupantList=oList;

        return houseDetail;
    }

//    public List<FacilityReport> getFacilityReportByUsername(String username){
//        //get employeeID by username
//        UserDaoImpl userDao = new UserDaoImpl();
//        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
//        int eID = employeeDao.getEmployeeByUserId(
//                userDao.findIdbyNmae(username)
//        ).getID();
//
//        FacilityReportDaoImpl FRDI = new FacilityReportDaoImpl();
//        return FRDI.getFacilityReportByEmployeeID(eID);
//    }

    public List<FacilityReportDetail> getFacilityReportDetailByFacilityReportID(int frID){
        FacilityReportDetailDaoImpl frddi = new FacilityReportDetailDaoImpl();
        return
                frddi.getFacilityReportDetailByFacilityReportID(frID);
    }

    public List<FacilityReportAndUsername> getFacilityReportByUsername(String username){
        //get employeeID by username
        UserDaoImpl userDao = new UserDaoImpl();
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        int eID = employeeDao.getEmployeeByUserId(
                userDao.findIdbyNmae(username)
        ).getID();
        //get houseId by eId (Note that houseId is in the form Employee)
        int hID = employeeDao.getHouseIdByEmployeeId(eID);
        //find all employee id that live in this house
        List<Integer> integers = employeeDao.getAllEmployeeInAHouseId(hID);
        FacilityReportDaoImpl facilityReportDao = new FacilityReportDaoImpl();
        List<FacilityReport> facilityReports = facilityReportDao.getFacilityReportByListOfEmployeeID(integers);

        FacilityReportDetailDaoImpl frdd = new FacilityReportDetailDaoImpl();
        List<FacilityReportAndUsername> output = new ArrayList<>();
        for(FacilityReport f : facilityReports){
            int uid = employeeDao.getUserIDByEmployeeID(f.getEmployeeID());
            String un = ((User)userDao.get(uid)).getUsername();
            FacilityReportAndUsername fa = new FacilityReportAndUsername();
            fa.setFacilityReport(f);
            fa.setUsername(un);
            //get facility report detail
            List<FacilityReportDetail> detailList = frdd.getFacilityReportDetailByFacilityReportID(f.getID());
            List<FacilityReportDetailAndUsername> detailAndUsernames = new ArrayList<>();
            if(detailList != null) {


                for (FacilityReportDetail detail : detailList) {
                    if (detail == null) {
                        continue;
                    }
                    FacilityReportDetailAndUsername fu = new FacilityReportDetailAndUsername();
                    fu.setFacilityReportDetail(detail);
                    fu.setUsername(
                            ((User) userDao.get(
                                    employeeDao.getUserIDByEmployeeID(f.getEmployeeID())
                            )).getUsername()
                    );
                    detailAndUsernames.add(fu);
                }
            }
            fa.setDetails(detailAndUsernames);
            output.add(fa);
        }
        return output;
    }

    public String saveOrUpdateComment(int reportDetailID, String newComment){
        FacilityReportDetailDaoImpl FRDDI = new FacilityReportDetailDaoImpl();
        if (FRDDI.get(reportDetailID) == null)
            return "reportDetailID doesn't exist";
        FacilityReportDetail targetDetail =
                (FacilityReportDetail) FRDDI.get(reportDetailID);
        targetDetail.setComment(newComment);
        targetDetail.setLastModificationDate(UtilFunction.getTodayDate());
        FRDDI.saveOrupdate(targetDetail);
        return "Edited Successfully";
    }

    public String saveOrUpdateReport(int reportID, String newDescription, boolean changeStatus){
        FacilityReportDaoImpl FRDI = new FacilityReportDaoImpl();
        if (FRDI.get(reportID)==null)
            return "reportID doesn't exist";
        FacilityReport report = (FacilityReport) FRDI.get(reportID);
        System.out.println(report.toString());
        if (!newDescription.equals(""))
            report.setDescription(newDescription);
        if (changeStatus){
            if (report.getStatus().equals("close"))
                report.setStatus("open");
            else if (report.getStatus().equals("open"))
                report.setStatus("close");
        }
        FRDI.saveOrupdate(report);
        System.out.println(report.toString());
        return "Edited Successfully";
    }

}

