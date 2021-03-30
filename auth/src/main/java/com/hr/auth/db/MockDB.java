package com.hr.auth.db;

//import com.icoding.vo.ClientInfoVo;

import org.junit.Test;

import java.util.*;

// 数据结构
public class MockDB {

    public static Set<String> T_TOKEN  = new HashSet<String>(); // token保存表
    @Test
    public void test(){
        System.out.println(T_TOKEN);
    }

    // 用户登出地址保存表  token， Alist
//    public static Map<String,List<ClientInfoVo>> T_CLIENT_INFO
//            = new HashMap<String, List<ClientInfoVo>>();


}
