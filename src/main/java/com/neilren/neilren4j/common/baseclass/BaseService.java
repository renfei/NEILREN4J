package com.neilren.neilren4j.common.baseclass;

import com.neilren.neilren4j.dao.TArticleMapper;
import com.neilren.neilren4j.dao.TCategoryMapper;
import com.neilren.neilren4j.dao.TSettingMapper;
import com.neilren.neilren4j.dao.TTagMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName BaseService
 * @Description TODO
 * @Date 2018/7/18 14:40
 */
public class BaseService extends BaseClass {
    @Autowired
    protected TSettingMapper settingMapper;
    @Autowired
    protected TTagMapper tagMapper;
    @Autowired
    protected TCategoryMapper categoryMapper;
    @Autowired
    protected TArticleMapper articleMapper;
}
