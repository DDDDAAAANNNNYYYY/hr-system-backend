package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Employee;
import com.hr.hrserver.pojo.OccupantRaw;
import com.hr.hrserver.pojo.User;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl implements EmployeeDao{

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    @Override
    public Employee getEmployeeByUserId(int id) {
        Query query = getCurrentSession().createQuery("from Employee e where e.UserID=:uid");
//        Transaction tx = getCurrentSession().beginTransaction();

        query.setParameter("uid", id);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        Employee e = (Employee)query.list().get(0);
//        tx.commit();
        return  e ;
    }
    public List<OccupantRaw> getSomeInfoByHouseID(int hID){
        String qry = "Select FirstName, PreferredName, CellPhone from Employee Where HouseID="+hID+"";
        Query query = getCurrentSession().createSQLQuery(qry);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        List<OccupantRaw> result = new ArrayList<>();
        for (int i=0;i<query.list().size();i++){
            OccupantRaw oR = new OccupantRaw();
            Object[] le = (Object[]) query.list().get(i);
            oR.FirstName = le[0].toString();
            oR.PreferredName = le[1].toString();
            oR.CellPhone = le[2].toString();
            result.add(oR);
        }
        return result;
    }

    public int getUserIDByEmployeeID(int eid){
        Query query = getCurrentSession().createQuery("from Employee e where e.ID=:eid");
        query.setParameter("eid", eid);
        if(CollectionUtils.isEmpty(query.list())) {
            return -1;
        }
        Employee e = (Employee)query.list().get(0);
        return  e.getUserID();
    }
    public int getHouseIdByEmployeeId(int eid){
        Query query = getCurrentSession().createQuery("from Employee e where e.ID=:eid");
        Transaction tx = getCurrentSession().beginTransaction();
        query.setParameter("eid", eid);
        if(CollectionUtils.isEmpty(query.list())) {
            return -1;
        }
        Employee e = (Employee)query.list().get(0);
        tx.commit();
        return  e.getHouseID();
    }

    public List<Integer> getAllEmployeeInAHouseId(int hid){
        //following code works, yet it returns all fields of employee instead of list of eid
//        Query query = getCurrentSession().createQuery("from Employee e where e.HouseID=:hid");
//        query.setParameter("hid", hid);
//        if(CollectionUtils.isEmpty(query.list())) {
//            return null;
//        }
//        return  query.list();
        String qry = "Select ID from Employee Where HouseID="+hid;
        Query query = getCurrentSession().createSQLQuery(qry);
        if(CollectionUtils.isEmpty(query.list())) {
            return null;
        }
        return query.list();
    }

    public List<Employee> getAllEmployee(){
        Query query = getCurrentSession().createQuery("from Employee");
        return query.list();
    }


    public Employee getEmployeeByUserName(String uname) {
        UserDaoImpl ud = new UserDaoImpl();
        String email = (String)ud.findEmailbyNmae(uname);

        Query query = getCurrentSession().createQuery("from  Employee e  where e.Email=:email");
//
        query.setParameter("email", email);
//
        Employee e = (Employee) query.list().get(0);

//
        return e;
    }






}
