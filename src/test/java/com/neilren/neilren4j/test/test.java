package com.neilren.neilren4j.test;

import java.io.File;
import java.util.Date;

/**
 * Created by neil on 19/06/2017.
 */
public class test {
    public static void main(String[] args) {
        String path = "/Users/neil/Downloads/jq/";
        File file = new File(path);
        File[] tempList = file.listFiles();
        System.out.println("该目录下对象个数：" + tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹：" + tempList[i]);
                new File(tempList[i].toString()).renameTo(new File(tempList[i].toString().replace("jquery-", "")));
                System.out.println("重命名：" + tempList[i].toString().replace("jquery-", ""));
            }
        }
    }
}
