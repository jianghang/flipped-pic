package com.github.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import com.github.model.PicUrlData;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class PicUrlDataListener extends AnalysisEventListener<PicUrlData> {

    @Override
    public void invoke(PicUrlData picUrlData, AnalysisContext analysisContext) {
//        log.info("cellMap: {}", analysisContext.readRowHolder().getCellMap());
        log.info("picUrlData: {}", JSON.toJSONString(picUrlData));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        if (extra.getType() == CellExtraTypeEnum.MERGE) {
            System.out.println(extra.getFirstColumnIndex());
            System.out.println(extra.getLastColumnIndex());
            System.out.println(extra.getText());
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("headMap: {}", JSON.toJSONString(headMap));
    }
}
