/**	
 * <br>
 * TALENTBASE 3.0 Copyright 2005 Neusoft, Co.ltd. All rights reserved.<br>
 * <br>			 
 * Package: com.file <br>
 * FileName: FileTest.java <br>
 * <br>
 * @version
 * @author zhqiang
 * @created 2013-10-12
 * @last Modified 
 * @history
 */

package com.file;

import java.io.File;
import java.io.IOException;

/**
 * {该处请说明该class的含义和作用}
 *  
 *  @author zhqiang
 *  @created 2013-10-12 下午04:34:55
 *  @lastModified       
 *  @history           
 */

public class FileTest {
    
    public static void main(String[] args){
        try {
            System.out.println("aaaaaaaaaa");
            File file = File.createTempFile("aaa", ".bb");
            System.out.println(file.getName());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}




