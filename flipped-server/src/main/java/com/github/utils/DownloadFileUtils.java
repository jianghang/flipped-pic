package com.github.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DownloadFileUtils {

    public static String download(String url, String filepath) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();

        File file = new File(filepath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[2048];
        int ch = 0;
        while ((ch = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, ch);
        }
        inputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();

        return filepath;
    }
}
