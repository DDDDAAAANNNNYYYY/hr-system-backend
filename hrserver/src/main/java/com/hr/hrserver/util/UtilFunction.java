package com.hr.hrserver.util;

import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilFunction {

    @SneakyThrows
    public static Date getTodayDate(){
        Date date=new Date();
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd");
        String date1=temp.format(date);
        Date date2=temp.parse(date1);

        return date2;
    }
}
