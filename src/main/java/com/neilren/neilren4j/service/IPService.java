package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BaseService;
import com.neilren.neilren4j.dao.TIpMapper;
import com.neilren.neilren4j.dbentity.TIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName IPService
 * @Description TODO
 * @Date 2018/8/5 10:01
 */
@Service
public class IPService extends BaseService {
    @Autowired
    private TIpMapper ipMapper;

    public TIp selectByIP(String ip) {
        return ipMapper.selectByIP(ip);
    }
}
