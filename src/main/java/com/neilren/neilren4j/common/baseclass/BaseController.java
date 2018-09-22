package com.neilren.neilren4j.common.baseclass;

import com.neilren.neilren4j.common.conf.Neilren4jConfig;
import com.neilren.neilren4j.service.HolidayService;
import com.neilren.neilren4j.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName BaseController
 * @Description TODO
 * @Date 2018/7/16 17:01
 */
public class BaseController extends BaseClass {
    @Autowired
    protected Neilren4jConfig neilren4jConfig;
    @Autowired
    protected SystemService systemService;
    @Autowired
    protected HolidayService holidayService;
    protected String siteName;
}
