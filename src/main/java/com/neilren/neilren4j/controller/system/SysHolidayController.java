package com.neilren.neilren4j.controller.system;

import com.neilren.neilren4j.common.baseclass.BaseSysController;
import com.neilren.neilren4j.common.entity.APIResult;
import com.neilren.neilren4j.common.entity.EasyuiDatagrid;
import com.neilren.neilren4j.dbentity.THoliday;
import com.neilren.neilren4j.service.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName SysHolidayController
 * @Description TODO
 * @Date 2018/8/5 22:36
 */
@Controller
@Slf4j
@RequestMapping("/system/holiday")
@RequiresRoles("admin")
public class SysHolidayController extends BaseSysController {
    @Autowired
    private HolidayService holidayService;

    @RequestMapping("list")
    public ModelAndView getList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/holiday/list");
        return mv;
    }

    @RequestMapping("getDateList")
    @ResponseBody
    public EasyuiDatagrid getDateList(int page, int rows) {
        EasyuiDatagrid easyuiDatagrid = new EasyuiDatagrid();
        holidayService.getAllHoliday(easyuiDatagrid, page, rows);
        return easyuiDatagrid;
    }

    @RequestMapping("saveHoliday")
    @ResponseBody
    public APIResult saveHoliday(THoliday holiday) {
        APIResult result = new APIResult();
        try {
            result.setSuccess(true);
            holidayService.saveHoliday(holiday);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(e.getMessage(), e);
        }

        return result;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
