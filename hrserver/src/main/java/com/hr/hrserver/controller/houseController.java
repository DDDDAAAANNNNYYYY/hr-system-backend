package com.hr.hrserver.controller;

import com.hr.hrserver.dao.EmployeeDaoImpl;
import com.hr.hrserver.dao.UserDaoImpl;
import com.hr.hrserver.pojo.HouseDetail;
import com.hr.hrserver.service.HousingAndFacilityReportService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class houseController {

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

}
