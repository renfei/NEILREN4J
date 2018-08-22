package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BaseService;
import com.neilren.neilren4j.dao.TLogAccessMapper;
import com.neilren.neilren4j.entity.AlicmapiIP;
import com.neilren.neilren4j.entity.LogAccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName LogService
 * @Description TODO
 * @Date 2018/8/1 13:11
 */
@Slf4j
@Service
public class LogService extends BaseService {
    @Autowired
    private TLogAccessMapper logAccessMapper;
    @Autowired
    private IpInfoService ipInfoService;

    public void writeAccessLog(LogAccess logAccess) {
        logAccessMapper.insert(logAccess);
    }

    @Async
    public void insertAccessLog(HttpServletRequest request) {
        LogAccess logAccess = new LogAccess(request);
        try {
            AlicmapiIP alicmapiIP = ipInfoService.getIpInfo(logAccess.getIp());
            if (alicmapiIP != null && alicmapiIP.getRet() == 200) {
                logAccess.setArea(alicmapiIP.getData().getArea());
                logAccess.setCountry(alicmapiIP.getData().getCountry());
                logAccess.setCity(alicmapiIP.getData().getCity());
                logAccess.setRegion(alicmapiIP.getData().getRegion());
                logAccess.setIsp(alicmapiIP.getData().getIsp());
            }
            writeAccessLog(logAccess);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            writeAccessLog(logAccess);
        }
    }
}
