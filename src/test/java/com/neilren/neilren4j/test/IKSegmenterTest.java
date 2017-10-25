package com.neilren.neilren4j.test;

import org.junit.Test;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neil on 16/06/2017.
 */
public class IKSegmenterTest {
    public static void main(String[] args) {
        test();
    }
    @Test
    public static void test(){
        //System.out.println(SearchService.IKAnalysis("测试一段话的分词效果"));
        List<String> keywordList = new ArrayList<String>();
        try {
            byte[] bt = "测试一段话的分词效果".getBytes();
            InputStream ip = new ByteArrayInputStream(bt);
            Reader read = new InputStreamReader(ip);
            IKSegmenter iks = new IKSegmenter(read, true);//true开启只能分词模式，如果不设置默认为false，也就是细粒度分割
            Lexeme t;
            while ((t = iks.next()) != null) {
                keywordList.add(t.getLexemeText());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(keywordList);
    }
}
