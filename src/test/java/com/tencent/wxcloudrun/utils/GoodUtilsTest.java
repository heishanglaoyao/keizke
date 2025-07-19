package com.tencent.wxcloudrun.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class GoodUtilsTest {



    public static void main(String[] args) {
        String str3 = "1箱*24包*10个";
        String str2 = "1箱*60片";
        String str1 = "个";

        // 测试用例
        List<Integer> numbers = new ArrayList<>();
        List<String> units = new ArrayList<>();
        GoodUtils.parseString(str2, numbers, units);
        System.out.println("输入: " + str2);
        System.out.println("数字: " + numbers);
        System.out.println("单位: " + units);

    }



}