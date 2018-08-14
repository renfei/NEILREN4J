package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BaseService;
import com.neilren.neilren4j.common.util.IllegalStrFilterUtil;
import org.springframework.stereotype.Service;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SecurityService
 * @Description TODO
 * @Date 2018/8/1 16:20
 */
@Service
public class SecurityService extends BaseService {
    public boolean checkRequest(Object[] args) {
        String str = String.valueOf(args);
        if (!IllegalStrFilterUtil.sqlStrFilter(str)) {
            return false;
        } else if (!IllegalStrFilterUtil.isIllegalStr(str)) {
            return false;
        }
        return true;
    }
}
