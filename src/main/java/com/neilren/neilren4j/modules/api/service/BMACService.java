package com.neilren.neilren4j.modules.api.service;

import com.alibaba.fastjson.JSON;
import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.modules.api.entity.BMACCardRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by neil on 09/08/2017.
 */
@Service
public class BMACService {

    private static String memcachedCardRecordKey = "BMAC_CardRecord_";
    @Autowired
    private MemcachedManager memcachedManager;

    public List<BMACCardRecord> inquiryCardRecord(String cardID, String pageNo) throws Exception {
        List<BMACCardRecord> bmacCardRecords = null;
        bmacCardRecords = (List<BMACCardRecord>) memcachedManager.get(memcachedCardRecordKey + cardID + "_" + pageNo);
        if (bmacCardRecords == null) {
            bmacCardRecords=new ArrayList<BMACCardRecord>();
            URL url = new URL("http://www.bmac.com.cn/cardTransQuery/inquiryCardRecordDt.jhtml");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);

            con.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; text/html; charset=utf-8");
            con.setConnectTimeout(1000);
            con.setReadTimeout(3000);
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.write("pageNo=" + pageNo + "&iscapt=true&capstr=1234&cardNo=" + cardID);
            osw.flush();
            osw.close();
            /////////////
            StringBuffer buffer = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(con
                    .getInputStream(), "utf-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
            String html = buffer.toString();
            List<BMACCardRecord> bmacCardRecords1 = new ArrayList<BMACCardRecord>(), bmacCardRecords2 = new ArrayList<BMACCardRecord>();
            bmacCardRecords1 = htmlToObjList(html, "<div id=\"chargeTab\".*?>([\\s\\S]*)<!--切换面板01结束-->");
            bmacCardRecords2 = htmlToObjList(html, "<div id=\"consumTab\".*?>([\\s\\S]*)<!--切换面板02结束-->");
            for (BMACCardRecord obj : bmacCardRecords1) {
                bmacCardRecords.add(obj);
            }
            for (BMACCardRecord obj : bmacCardRecords2) {
                bmacCardRecords.add(obj);
            }
            //排序
            Collections.sort(bmacCardRecords);
            memcachedManager.set(memcachedCardRecordKey + cardID + "_" + pageNo, bmacCardRecords, Global.MemcachedExpire);
            return bmacCardRecords;
        } else
            return bmacCardRecords;
    }

    private List<BMACCardRecord> htmlToObjList(String html, String ma) {
        List<BMACCardRecord> bmacCardRecords = new ArrayList<BMACCardRecord>();
        String htmlTemp = "";
        Matcher m = Pattern.compile(ma).matcher(html);
        List<String> list1 = new ArrayList<String>();
        while (m.find()) {
            list1.add(m.group());
        }
        for (int i = 0; i < list1.size(); i++) {
            htmlTemp = htmlTemp + list1.get(i);
        }
        htmlTemp = htmlTemp.replaceAll("<.*?>", "").replace("\t", "").replace(" ", "");
        String[] arrHtml = htmlTemp.split("\n");
        //去除空行
        List<String> tmp = new ArrayList<String>();
        for (String str : arrHtml) {
            if (str != null && str.length() != 0) {
                tmp.add(str);
            }
        }
        arrHtml = tmp.toArray(new String[0]);
        if (ma.indexOf("chargeTab") > 0) {
            BMACCardRecord obj = new BMACCardRecord();
            //1:充值
            for (int i = 0, j = 1; i <= arrHtml.length; i++, j++) {
                if (j >= 6) {
                    j = 1;
                    bmacCardRecords.add(obj);
                    obj = new BMACCardRecord();
                } else
                    switch (j) {
                        case 1:
                            obj = new BMACCardRecord();
                            obj.setType(1);
                            obj.setDate(arrHtml[i] + " " + arrHtml[i + 1]);
                            break;
                        case 2:
                            obj.setType(1);
                            obj.setDate(arrHtml[i - 1] + " " + arrHtml[i]);
                            break;
                        case 3:
                            obj.setMoney(arrHtml[i]);
                            break;
                        case 4:
                            obj.setBefore(arrHtml[i]);
                            break;
                        case 5:
                            obj.setAfter(arrHtml[i]);
                            break;
                        default:
                            break;
                    }
            }
        } else {
            BMACCardRecord obj = new BMACCardRecord();
            //2:消费
            for (int i = 0, j = 1; i <= arrHtml.length; i++, j++) {
                if (j >= 7) {
                    j = 1;
                    bmacCardRecords.add(obj);
                    obj = new BMACCardRecord();
                } else
                    switch (j) {
                        case 1:
                            obj = new BMACCardRecord();
                            obj.setType(0);
                            obj.setDate(arrHtml[i] + " " + arrHtml[i + 1]);
                            break;
                        case 2:
                            obj.setType(0);
                            obj.setDate(arrHtml[i - 1] + " " + arrHtml[i]);
                            break;
                        case 3:
                            obj.setScene(arrHtml[i]);
                            break;
                        case 4:
                            obj.setMoney(arrHtml[i]);
                            break;
                        case 5:
                            obj.setBalance(arrHtml[i]);
                        case 6:
                            obj.setCumulative(arrHtml[i]);
                            break;
                        default:
                            break;
                    }
            }
        }

        return bmacCardRecords;
    }
}
