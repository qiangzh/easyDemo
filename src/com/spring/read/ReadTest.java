package com.spring.read;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.ResourceUtils;

public class ReadTest {

    public static void main(String[] args) throws Exception {
        List list = new ArrayList();
        //        list.add("classpath:com/neusoft/tsdcms/core/entity/");
        //        list.add("classpath:com/neusoft/tsdcms/core/entity/");
        //        list.add("classpath:com/neusoft/tsdcms/cms/entity/");
        //        list.add("classpath:com/neusoft/tsdcms/article/entity/");
        //        list.add("classpath:com/neusoft/tsdcms/download/entity/");
        //        list.add("classpath:com/neusoft/tsdcms/auxiliary/entity/");
        //        list.add("classpath:com/neusoft/tsdcms/supply_demand/entity/");
        list.add("classpath:com/spring/read");

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            String url = (String) iter.next();
            File file = ResourceUtils.getFile(url);
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File tempFile = files[i];
                if (tempFile.getName().endsWith(".xml")) {
                    String tempUrl = url.substring("classpath:".length());
                    System.out.println("<value>"+tempUrl+tempFile.getName()+"</value>");
                }
            }
        }

    }
}
