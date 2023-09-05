package com.kuku.JUNIT5;

import com.kuku.Utility.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MethodSourceTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name) {
        System.out.println("name = " + name);
    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void test2(Map<String,String> userInfo){
        System.out.println("userInfo = " + userInfo);
        System.out.println("Name = " + userInfo.get("Name"));
        System.out.println("Email = " + userInfo.get("Email"));
        System.out.println("Password = " + userInfo.get("Password"));
    }

    public static List<Map<String,String>> getExcelData(){
        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/data/Library.xlsx","Bookit");

        return excelUtil.getDataList();
    }

                // <----------EXAMPLE------------>

    public static List<String> getNames(){

        List<String> nameList = Arrays.asList("Steven", "TJ", "Kimberly", "Mike");
        return nameList;
    }

}
