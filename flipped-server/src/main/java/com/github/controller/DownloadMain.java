package com.github.controller;

import com.github.utils.DownloadFileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class DownloadMain {
    public static void main(String[] args) throws IOException {
        // DownloadFileUtils.download("http://imgus.entsku.com/imgs/440/1200/2020/04-07/113420888wEyvFjtXg8gM.jpg", "F:/static/202005/img/tutu1.jpg");
        String todayStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Path path = Paths.get("F:/image", todayStr, UUID.randomUUID().toString());
        System.out.println(path.toString());
    }
}
