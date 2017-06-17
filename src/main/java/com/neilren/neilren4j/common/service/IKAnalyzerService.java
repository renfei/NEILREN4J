package com.neilren.neilren4j.common.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 分词服务
 * @author NeilRen
 * @version 1.0
 */
@Service
public class IKAnalyzerService extends BaseService {
    public List<String> analyzer(String wd) throws Exception {
        //创建IKAnalyzer中文分词对象
        IKAnalyzer analyzer = new IKAnalyzer();
        // 使用智能分词
        analyzer.setUseSmart(true);
        return analysisResult(analyzer, wd);
    }

    /**
     * 打印出给定分词器的分词结果
     *
     * @param analyzer 分词器
     * @param keyWord  关键词
     * @throws Exception
     */
    private static List<String> analysisResult(Analyzer analyzer, String keyWord)
            throws Exception {
        TokenStream tokenStream = analyzer.tokenStream("content",
                new StringReader(keyWord));
        tokenStream.addAttribute(CharTermAttribute.class);
        List<String> stringList = new ArrayList<String>();
        while (tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream
                    .getAttribute(CharTermAttribute.class);
            stringList.add(charTermAttribute.toString());

        }
        return stringList;
    }
}
