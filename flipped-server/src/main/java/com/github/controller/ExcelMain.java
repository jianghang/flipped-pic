package com.github.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import com.github.listener.MapDataListener;
import com.github.model.ImageUrlCoordinate;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ExcelMain {
    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("F:/static", "/202005/1d70637f-a800-41c3-a4fb-16dab23c4867demo.xlsx");
//        Path path = Paths.get("F:/static", "/202005/KUWTBandanasxls_20200426161025-1.xls");
        Path path = Paths.get("F:/static", "/202005/自己系统导出的.xls");
//        EasyExcel.read(path.toFile(), new MapDataListener()).sheet("Template").doRead();
        System.out.println(path.toString());
//        ExcelReader excelReader = EasyExcel.read(path.toFile(),new MapDataListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet("Template").build();
//        excelReader.read(readSheet);
//        excelReader.finish();

        POIFSFileSystem fileSystem = new POIFSFileSystem(path.toFile());
        Workbook workbook = new HSSFWorkbook(fileSystem);
        Sheet sheet = workbook.getSheet("Template");//todo 配置文件读取
        int rowNum = 2;//todo 可以修改为配置文件读取
        Row row = sheet.getRow(rowNum);
        short firstCellNum = row.getFirstCellNum();
        short lastCellNum = row.getLastCellNum();
        Table<Integer, Integer, String> imageUrlTable = HashBasedTable.create();
        List<Integer> imageCellNum = Lists.newArrayList();
        for (short i = firstCellNum; i < lastCellNum; i++) {
            String cellValue = row.getCell(i).getStringCellValue();
//            System.out.println(cellValue);
            if (StringUtils.isBlank(cellValue)) {
                continue;
            }
            if (cellValue.equals("main_image_url")) {//todo 配置文件读取
                System.out.println(cellValue);
                imageCellNum.add((int) i);
            }
            if (cellValue.contains("other_image_url")) {//todo 配置文件读取
                System.out.println(cellValue);
                imageCellNum.add((int) i);
            }
        }

        System.out.println(JSON.toJSONString(imageCellNum));
        Cell cell;
        rowNum++;
        List<ImageUrlCoordinate> imageUrlCoordinateList = Lists.newArrayList();
        ImageUrlCoordinate imageUrlCoordinate;
        while (Objects.nonNull(row = sheet.getRow(rowNum))) {
            for (Integer cellNum : imageCellNum) {
                cell = row.getCell(cellNum);
                if (Objects.isNull(cell)) {
                    break;
                }
                imageUrlTable.put(cellNum, rowNum, cell.getStringCellValue());
                imageUrlCoordinate = new ImageUrlCoordinate(rowNum, cellNum, cell.getStringCellValue() + "修改1");
                imageUrlCoordinateList.add(imageUrlCoordinate);
            }
            rowNum++;
        }
        System.out.println(JSON.toJSONString(imageUrlCoordinateList));

        for (ImageUrlCoordinate urlCoordinate : imageUrlCoordinateList) {
            cell = sheet.getRow(urlCoordinate.getRow()).getCell(urlCoordinate.getCell());
            cell.setCellValue(urlCoordinate.getUrl());
        }
        path = Paths.get("F:/static", "/202005/", UUID.randomUUID().toString() + ".xls");
        FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
}
