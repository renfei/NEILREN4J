package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BaseService;
import com.neilren.neilren4j.dao.TLogAccessMapper;
import com.neilren.neilren4j.entity.LogAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName LogService
 * @Description TODO
 * @Date 2018/8/1 13:11
 */
@Service
public class LogService extends BaseService {
    @Autowired
    private TLogAccessMapper logAccessMapper;

    public void writeAccessLog(LogAccess logAccess) {
        logAccessMapper.insert(logAccess);
    }
}
