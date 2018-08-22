package com.neilren.neilren4j.service;

import com.neilren.neilren4j.common.baseclass.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName WaringService
 * @Description TODO
 * @Date 2018/8/1 15:05
 */
@Slf4j
@Service
public class WaringService extends BaseService {
    public void waring(String s, Object o) {
        log.warn(s, o);
    }
}
