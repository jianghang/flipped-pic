package com.github.controller;

import com.github.utils.DownloadFileUtils;

import java.io.IOException;

public class DownloadMain {
    public static void main(String[] args) throws IOException {
        DownloadFileUtils.download("http://imgus.entsku.com/imgs/440/1200/2020/04-07/113420888wEyvFjtXg8gM.jpg", "F:/static/202005/img/tutu1.jpg");
    }
}
