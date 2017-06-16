package com.neilren.neilren4j.common.config;

import java.util.Map;

import com.google.common.collect.Maps;

import com.neilren.neilren4j.common.utils.PropertiesLoader;
import com.neilren.neilren4j.common.utils.StringUtils;

/**
 * 全局配置类
 * @author NeilRen
 * @version 2017-06-07
 */
public class Global {
    /**
     * 获取当前对象实例
     */
    public static Global getInstance() {
        return global;
    }

    /**
     * 当前对象实例
     */
    private static Global global = new Global();

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("neilren.properties");

    /**
     * 显示/隐藏
     */
    public static final String SHOW = "1";
    public static final String HIDE = "0";

    /**
     * 是/否
     */
    public static final String YES = "1";
    public static final String NO = "0";

    /**
     * 对/错
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * 上传文件基础虚拟路径
     */
    public static final String USERFILES_BASE_URL = "/userfiles/";

    /**
     * 获取配置
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * 获取管理端根路径
     */
    public static String getAdminPath() {
        return getConfig("adminPath");
    }

    /**
     * 获取前端根路径
     */
    public static String getFrontPath() {
        return getConfig("frontPath");
    }

    /**
     * 获取URL后缀
     */
    public static String getUrlSuffix() {
        return getConfig("urlSuffix");
    }

    /**
     * 页面获取常量
     */
    public static Object getConst(String field) {
        try {
            return Global.class.getField(field).get(null);
        } catch (Exception e) {
            // 异常代表无配置，这里什么也不做
        }
        return null;
    }

    /**
     * Memcached缓存时间
     */
    public static int MemcachedExpire = 10000;
}
