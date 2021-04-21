package com.github.entropyfeng.apartment.util;

import com.github.entropyfeng.apartment.exception.BusinessBusyException;
import com.github.entropyfeng.common.exception.BusinessException;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.io.*;
import java.util.Date;

public class FileUtil {

    private static final Logger logger= LoggerFactory.getLogger(FileUtil.class);


    public static File downloadLocalFile(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        return resource.getFile();
    }

    @NotNull public static byte[] downloadLocal(String fileName) {
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
        throw new BusinessBusyException("业务繁忙");
    }

    public static ResponseEntity<FileSystemResource> export(@NotNull File file) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("fileName",file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }
}
