package com.hibernate;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringEscapeUtils;

public class SqlTest {
    

    // hibernate SqlQuery 
    private static void initParameterBookKeeping(String queryString) {
        StringTokenizer st = new StringTokenizer(queryString, " \n\r\f\t,()=<>&|+-=/*'^![]#~\\");
        Set result = new HashSet();

        while (st.hasMoreTokens()) {
          String string = st.nextToken();
          System.out.println(string);
          if (string.startsWith(":")) {
            result.add(string.substring(1));
            //System.out.println(string.substring(1));
          }
        }
      }
    
    public static void main(String[] args){
        String sql1 = "select * from tb_inf_employee where c_name like '%]:%' and c_name = :name";
        // hibernate 参数匹配
        initParameterBookKeeping(sql1);
        
        //--、/* 处理
        String sql2 = "select * from tb_inf_employee where c_name='--' and c_name='"+"1' or '1'='1"+"'";
        System.out.println(sql2);
        System.out.println(StringEscapeUtils.escapeSql(sql2));
        
    }

}
