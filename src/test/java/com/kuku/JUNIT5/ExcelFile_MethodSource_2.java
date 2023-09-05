package com.kuku.JUNIT5;

import com.kuku.Utility.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ExcelFile_MethodSource_2 {

    @Test
    public void test1() {

        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/data/Library.xlsx","Short");
        System.out.println("excelUtil.columnCount() = " + excelUtil.columnCount());

         for (Map<String,String> row : excelUtil.getDataList()){
             System.out.println("row = " + row);
         }

    }
}
