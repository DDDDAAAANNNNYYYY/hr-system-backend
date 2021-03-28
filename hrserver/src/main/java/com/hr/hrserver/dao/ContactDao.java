package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> getAllContactByEmployeeID(int eid);
}
