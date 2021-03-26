package com.hr.hrserver.dao;

public interface registrationTokenDao {
    public boolean isValidToken(String token);
}
