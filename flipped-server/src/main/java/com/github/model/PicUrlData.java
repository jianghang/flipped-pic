package com.github.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PicUrlData {

//    @ExcelProperty("字符串标题")
//    private String string;

//    @ExcelProperty("日期标题")
//    private Date date;

//    @ExcelProperty(value = "数字标题", index = 2)
//    private Double doubleData;

    @ExcelProperty(value = {"pic_url", "pic_url"}, index = 3)
    private String picUrl;

    @ExcelProperty(value = {"main_body_url", "body_url1"}, index = 4)
    private String bodyPicUrl1;

    @ExcelProperty(value = {"main_body_url", "body_url2"}, index = 5)
    private String bodyPicUrl2;
}
