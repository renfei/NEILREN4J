package com.neilren.neilren4j.common.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by neil on 29/06/2017.
 */
@Service
public class HttpClientService extends BaseService {
    /**
     * 构建请求参数
     *
     * @param params
     * @return
     */
    public String buildParams(Map<String, Object> params) {
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> e : params.entrySet()) {
                sbParams.append(e.getKey());
                sbParams.append("=");
                sbParams.append(e.getValue());
                sbParams.append("&");
            }
            return sbParams.toString();
        } else
            return "";
    }

    /**
     * 构造URL连接对象
     *
     * @param url
     * @return
     * @throws IOException
     */
    public URLConnection buildURLConnection(String url) throws IOException {
        URLConnection connection = null; //创建的http连接
        //建立连接
        connection = new URL(url).openConnection();
        connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        connection.setRequestProperty("connection", "keep-alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        return connection;
    }

    /**
     * 发送get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 请求结果
     * @throws IOException
     */
    public String sendGet(String url, Map<String, Object> params) throws IOException {
        StringBuffer buffer = new StringBuffer(); //用来拼接参数
        StringBuffer result = new StringBuffer(); //用来接受返回值
        URL httpUrl = null; //HTTP URL类 用这个类来创建连接
        URLConnection connection = null; //创建的http连接
        BufferedReader bufferedReader = null; //接受连接受的参数
        String requestParams = buildParams(params);
        //如果存在参数，我们才需要拼接参数 类似于 localhost/index.html?a=a&b=b
        if (!requestParams.equals("")) {
            url = url + "?" + requestParams;
        }
        connection = buildURLConnection(url);
        connection.connect();
        //接受连接返回参数
        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        return result.toString();
    }

    /**
     * 发送Post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 请求结果
     * @throws IOException
     */
    public String sendPost(String url, Map<String, Object> params) throws IOException {
        StringBuffer result = new StringBuffer(); //用来接受返回值
        URLConnection connection; //创建的http连接
        PrintWriter printWriter;
        BufferedReader bufferedReader; //接受连接受的参数
        //建立连接
        connection = buildURLConnection(url);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        printWriter = new PrintWriter(connection.getOutputStream());
        printWriter.print(buildParams(params));
        printWriter.flush();
        connection.connect();
        //接受连接返回参数
        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        return result.toString();
    }

    /**
     * 发送Post请求
     * 为百度实时推送重载的方法，因为百度实时推送没有参数名
     * 任霏 2017-06-29 19:32:59
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 请求结果
     * @throws IOException
     */
    public String sendPost(String url, String[] params) throws IOException {
        StringBuffer result = new StringBuffer(); //用来接受返回值
        URLConnection connection; //创建的http连接
        PrintWriter printWriter;
        BufferedReader bufferedReader; //接受连接受的参数
        //建立连接
        connection = buildURLConnection(url);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        printWriter = new PrintWriter(connection.getOutputStream());
        //发送请求参数
        String param = "";
        for (String s : params) {
            param += s + "\n";
        }
        printWriter.print(param);
        printWriter.flush();
        connection.connect();
        //接受连接返回参数
        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        return result.toString();
    }
}
