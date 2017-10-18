package com.neilren.neilren4j.common.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.dao.IPDBDao;
import com.neilren.neilren4j.common.entity.IPDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class IPDBService {
    private static String memcachedArticleListKey = "IPDBService_ip";

    @Autowired
    private IPDBDao ipdbDao;
    @Autowired
    private MemcachedManager memcachedManager;

    public String saveFile(CommonsMultipartFile file) throws IOException {
        String filePath = "/tmp/neilren4j_ip_" + new Date().getTime() + file.getOriginalFilename();
        File newFile = new File(filePath);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        return filePath;
    }

    /**
     * 更新IP数据表
     *
     * @param filePath 文件路径
     * @param version  IP数据库版本号
     * @return
     * @throws IOException
     */
    public int updateIPDB(String filePath, String version) throws IOException {
        File newFile = new File(filePath);
        //编辑List对象
        List<IPDBObject> ipdbObjectList = new ArrayList<IPDBObject>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(newFile),
                "UTF-8"));
        String lineTxt = null;
        while ((lineTxt = br.readLine()) != null) {
            String[] ips = lineTxt.split("\t");
            if (ips[0].toLowerCase().equals("startip")) {
                continue;
            }
            IPDBObject ipdbObject = new IPDBObject();
            ipdbObject.setStartIP(ips[0]);
            ipdbObject.setEndIP(ips[1]);
            ipdbObject.setCountry(ips[2]);
            ipdbObject.setLocal(ips.length < 4 ? "" : ips[3]);
            ipdbObject.setVersion(version);
            ipdbObjectList.add(ipdbObject);
        }
        br.close();
        ipdbDao.cloneTable();
        //拆分成多组
        if (ipdbObjectList.size() > 300) {
            while (ipdbObjectList.size() > 300) {
                ipdbDao.inserts(ipdbObjectList.subList(0, 300));
                if (301 > ipdbObjectList.size() - 300) {
                    ipdbDao.inserts(ipdbObjectList);
                    break;
                }
                ipdbObjectList = ipdbObjectList.subList(301, ipdbObjectList.size() - 300);
            }

        } else {
            //更新数据库
            ipdbDao.inserts(ipdbObjectList);
        }
        ipdbDao.rename();
        //删除上传的文件
        if (newFile.exists() && newFile.isFile()) {
            newFile.delete();
        }
        return 1;
    }

    public IPDBObject queryByIP(String IP) {
        if (isboolIp(IP)) {
            IPDBObject ipdbObject = null;
            ipdbObject = (IPDBObject) memcachedManager.get(memcachedArticleListKey + IP);
            if (ipdbObject == null) {
                ipdbObject = ipdbDao.selectByIP(IP);
                if (ipdbObject == null)
                    return null;
                memcachedManager.set(memcachedArticleListKey + IP, ipdbObject, Global.MemcachedExpire);
            }
            return ipdbObject;
        } else
            return null;
    }

    /**
     * 判断是否为合法IP * @return the ip
     */
    public boolean isboolIp(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
}
