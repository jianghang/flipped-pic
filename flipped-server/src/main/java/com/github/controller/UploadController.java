package com.github.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.github.listener.MapDataListener;
import com.github.listener.PicUrlDataListener;
import com.github.model.PicUrlData;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        log.info("file length: {}", multipartFiles.length);
        for (MultipartFile multipartFile : multipartFiles) {
            Path path = Paths.get("F:/static", "/202005/", UUID.randomUUID().toString() + multipartFile.getOriginalFilename());
            if (!path.toFile().getParentFile().exists()) {
                path.toFile().getParentFile().mkdir();
            }
            multipartFile.transferTo(path);
            List<String> fileTypeList = Splitter.on(".").splitToList(multipartFile.getOriginalFilename());
            String fileType = fileTypeList.get(fileTypeList.size() - 1);
            log.info("fileType: {}", fileType);
            EasyExcel.read(path.toFile(), new MapDataListener())
                    .sheet("Template").doRead();

//            List<PicUrlData> picUrlDataList = dataList();
//            Path excelPath = Paths.get("F:/static", "/202005/", UUID.randomUUID().toString() + ".xlsx");
//            EasyExcel.write(excelPath.toFile(), PicUrlData.class).withTemplate(path.toFile()).sheet().doWrite(picUrlDataList);
        }

        return "success";
    }

    private List<PicUrlData> dataList() {
        List<PicUrlData> picUrlDataList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            PicUrlData picUrlData = new PicUrlData();
            picUrlData.setBodyPicUrl1("body1_" + i);
            picUrlData.setBodyPicUrl2("body2_" + i);
            picUrlData.setPicUrl("pic_" + i);
            picUrlDataList.add(picUrlData);
        }
        return picUrlDataList;
    }
}
