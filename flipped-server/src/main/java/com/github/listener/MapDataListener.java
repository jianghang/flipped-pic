package com.github.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class MapDataListener extends AnalysisEventListener<Map<Integer, String>> {

    @Override
    public void invoke(Map<Integer, String> mapData, AnalysisContext analysisContext) {
        log.info("data: {}", JSON.toJSONString(mapData));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        headMap.get(1).getRowIndex();
    }
}
