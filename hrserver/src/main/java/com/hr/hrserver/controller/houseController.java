package com.hr.hrserver.controller;

import com.hr.hrserver.dao.EmployeeDaoImpl;
import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.pojo.FacilityReportAndUsername;
import com.hr.hrserver.pojo.FacilityReportDetail;
import com.hr.hrserver.pojo.HouseDetail;
import com.hr.hrserver.service.HousingAndFacilityReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class houseController {
    @Autowired
    HousingAndFacilityReportService HAFRS;
//    = new HousingAndFacilityReportService();

    @RequestMapping("/houseDetail")
    public HouseDetail fetchHouseDetailByUserName(@RequestBody String username){
        System.out.println(username);
        System.out.println("we get here--house a");
        UserDaoImpl userDao = new UserDaoImpl();
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        int uid = userDao.findIdbyNmae(username);
        int eid = employeeDao.getEmployeeByUserId(uid).getID();
        System.out.println("we get here--house b");
        HousingAndFacilityReportService HaFRS = new HousingAndFacilityReportService();

        return HaFRS.getHouseDetailByEmployeeID(eid);
    }

    @RequestMapping("/facilityReport")
    @ResponseBody
    //username -> employeeId -> houseID -> all employee id that has this houseID
    // -> all facilityReport Generated by these employees
    public List<FacilityReportAndUsername> fetchFacilityReportByUserName(@RequestBody  String username){
        System.out.println("GET HERE FACILITY A");
        System.out.println(username);
        List<FacilityReportAndUsername> list = HAFRS.getFacilityReportByUsername("Dany");
        System.out.println(list);
        return list;
    }

    @RequestMapping("/facilityReportDetail")
    @ResponseBody
    public List<FacilityReportDetail> fetchFacilityReportDetailByFacilityReportID(int id){

        return
                HAFRS.getFacilityReportDetailByFacilityReportID(id);
    }

    @RequestMapping("/facilityReportAddComment")
    @ResponseBody
//return the comment id
    public String addCommentToReport(@RequestBody  Map<String ,String> report){

        HAFRS.addNewCommentToReport(report);
        String comment = report.get("comment");

        return comment;
    }

    @RequestMapping("/facilityAddReport")
    @ResponseBody
//return id of the added report
    public String addReport(@RequestBody Map<String, String> data){
//        Map<Object, Object> data = new HashMap<Object, Object>();
//        data.put("title",title);
//        data.put("username",username);
//        data.put("description",description);
        System.out.println("report add get here b");
        System.out.println(data);
        HAFRS.addNewReport(data);
        return "Add Successful";
    }

    @RequestMapping("/facilityEditComment")
    @ResponseBody
    public String editComment(@RequestBody  Map<String,String> newComment){
        int reportDetailID = Integer.parseInt(newComment.get(""));
        String comment = newComment.get("comment");
        HAFRS.saveOrUpdateComment(reportDetailID, comment);

        return comment;

    }

//    @RequestMapping("/facilityEditReport")
//    @ResponseBody
//    public String editReport(int reportID, String newDescription, boolean changeStatus){
//        HAFRS.saveOrUpdateReport(reportID,newDescription,changeStatus);
//        return "Edit Success";
//
//    }




}
