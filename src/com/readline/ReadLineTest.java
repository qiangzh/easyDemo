package com.readline;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.cglib.core.CollectionUtils;

public class ReadLineTest {

    public static void main(String[] args) throws Exception {
        String filePath = "L:/workspace/Test/src/com/readline/Test.txt";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedReader dr = new BufferedReader(new InputStreamReader(fileInputStream));

        String str;
        Map<String,String> map = new HashMap();
        while ((str = dr.readLine()) != null) {
            int start = "L:\\workspace\\tb_jituan\\".length();
            int end = str.indexOf(".java(") + 5;
            str = str.substring(start, end);
            str = str.replace('\\', '/');
            map.put(str, str);
        }
        List list = new ArrayList();
        for(Iterator iter =map.entrySet().iterator();iter.hasNext();){
            Entry entry = (Entry) iter.next();   
            list.add((String)entry.getKey());            
        }
        Collections.sort(list);
        
        for(Iterator iter =list.iterator();iter.hasNext();){
            System.out.println(iter.next());
        }

    }
}
