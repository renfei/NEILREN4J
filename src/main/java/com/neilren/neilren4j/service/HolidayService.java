package com.neilren.neilren4j.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neilren.neilren4j.common.baseclass.BasePageService;
import com.neilren.neilren4j.common.entity.EasyuiDatagrid;
import com.neilren.neilren4j.dao.THolidayMapper;
import com.neilren.neilren4j.dbentity.THoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName HolidayService
 * @Description TODO
 * @Date 2018/7/18 17:02
 */
@Service
public class HolidayService extends BasePageService {
    @Autowired
    private THolidayMapper holidayMapper;

    public THoliday getHoliday() {
        Date now = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(now);
        // 将时分秒,毫秒域清零
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        return holidayMapper.selectHolidayByDate(cal1.getTime(), cal1.getTime());
    }

    public void getAllHoliday(EasyuiDatagrid easyuiDatagrid, int page, int rows) {
        Page pages = PageHelper.startPage(page, rows);
        easyuiDatagrid.setRows(holidayMapper.selectAll());
        easyuiDatagrid.setTotal(pages.getTotal());
    }

    public int saveHoliday(THoliday holiday) {
        return holidayMapper.insert(holiday);
    }
}
