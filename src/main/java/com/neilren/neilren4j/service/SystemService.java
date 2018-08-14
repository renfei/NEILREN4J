package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BaseService;
import com.neilren.neilren4j.dbentity.TSetting;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SysSettingService
 * @Description TODO
 * @Date 2018/7/20 21:57
 */
@Service
public class SystemService extends BaseService {
    public String getSiteName() {
        List<TSetting> settings = getSettingByKey("sitename");
        if (settings != null && settings.size() > 0) {
            return settings.get(0).getValue();
        } else {
            return "";
        }
    }

    public String getSiteDescription() {
        List<TSetting> settings = getSettingByKey("description");
        if (settings != null && settings.size() > 0) {
            return settings.get(0).getValue();
        } else {
            return "";
        }
    }

    public String getSiteKeywords() {
        List<TSetting> settings = getSettingByKey("keywords");
        if (settings != null && settings.size() > 0) {
            return settings.get(0).getValue();
        } else {
            return "";
        }
    }

    public String gerHeadScript(){
        List<TSetting> settings = getSettingByKey("headscript");
        if (settings != null && settings.size() > 0) {
            return settings.get(0).getValue();
        } else {
            return "";
        }
    }

    public List<TSetting> getSettingByKey(String key) {
        return settingMapper.selectByKey(key);
    }
}
