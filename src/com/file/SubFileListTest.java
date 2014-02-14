package com.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 
 *  获取指定目录下的文件名
 *  @author zhqiang
 *  @created 2013-11-5 上午09:10:16
 *  @lastModified       
 *  @history
 */
public class SubFileListTest {

    public static void main(String[] args) throws Exception {
        String filePath = "L:/workspace/WebService/WebContent/WEB-INF/lib";
        File file = new File(filePath);

        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".jar")) {
                    return true;
                }
                return false;
            }
        });

        File iterFile;
        for (int i = 0, j = files.length; i < j; i++) {
            iterFile = files[i];
            System.out.println(iterFile.getName());

        }

    }
}
