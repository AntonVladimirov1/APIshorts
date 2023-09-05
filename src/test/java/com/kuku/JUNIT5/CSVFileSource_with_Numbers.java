package com.kuku.JUNIT5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CSVFileSource_with_Numbers {

@ParameterizedTest
    @CsvFileSource(resources = "/data/numbers.csv",numLinesToSkip = 1)

    public void test1(int n1,int n2,int sum) {

    System.out.println(n1+"+" + n2 + " =" + sum);

}


}
