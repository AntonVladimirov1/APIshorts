package com.kuku.JUNIT5;

import com.kuku.Utility.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExcelFile_MethodSource {


    public static List<Map<String, String>> readExcel(){

        ExcelUtil readExcel = new ExcelUtil("src/test/resources/data/Library.xlsx","Short");

        List<Map<String, String>> dataList = readExcel.getDataList();
        for (Map<String, String> map : dataList) {
            System.out.println("map = " + map);
        }
        return dataList;
    }

    @ParameterizedTest
    @MethodSource("readExcel")
    void excelRead(Map<String,String> eachMap){
        System.out.println(eachMap);
    }

    //    <----------EXAMPLE---------->

    public static List<Integer> data(){

        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        return integers;
        // or like this
        //return new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    }

    @ParameterizedTest
    @MethodSource("data")
    public void useData(int a){
        System.out.println("a = " + a);
    }
}
