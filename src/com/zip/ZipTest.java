package com.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipTest {
    public static void main(String[] args) throws Exception {

        File temp = File.createTempFile("temp", ".zip");
        OutputStream outputStream = new FileOutputStream(temp);

        String path = ZipTest.class.getClassLoader().getResource("").getPath()+"com/zip/";

        InputStream inputStream = new FileInputStream(path + "resource.xml");
        int bytesRead;
        int bufferSize = 8192;
        byte buffer[] = new byte[bufferSize];
        while ((bytesRead = inputStream.read(buffer, 0, bufferSize)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outputStream.close();

        ZipFile zin = new ZipFile(temp);
        File tempFile;
        ZipEntry entry;
        Map fileMap = new LinkedHashMap();
        for (Enumeration e = zin.entries(); e.hasMoreElements(); fileMap.put(entry.getName(), tempFile)) {
            tempFile = File.createTempFile("temp", ".xls");
            outputStream = new FileOutputStream(tempFile);
            entry = (ZipEntry) e.nextElement();
            InputStream entryinputStream = zin.getInputStream(entry);
            while ((bytesRead = entryinputStream.read(buffer, 0, bufferSize)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
        }
        inputStream.close();

    }
}
