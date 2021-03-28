package com.hr.hrserver.dao;

import com.hr.hrserver.pojo.PersonalDocument;

import java.util.List;

public interface PersonalDocumentDao {
    public List<PersonalDocument> getPersonalDocumentByEmployeeID(int eid);
    public String getNewestDocumentPathByPersonalIDAndTitle(int pid, String title);
}
