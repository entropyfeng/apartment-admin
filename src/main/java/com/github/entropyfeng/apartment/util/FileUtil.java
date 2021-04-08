package com.github.entropyfeng.apartment.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.*;

public class FileUtil {

    public static byte[] downloadLocal(String fileName) {
        String path = "excel/BatchInsertStudentTemplate.xlsx";
        Resource resource = new ClassPathResource(fileName);
        byte[] bytes;
        try {
            File file = resource.getFile();
            int fileSize = (int) file.length();
            bytes = new byte[fileSize];
            FileInputStream fileInputStream = new FileInputStream(file);
            int actualSize = fileInputStream.read(bytes);
            if (actualSize == fileSize) {
                return bytes;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
