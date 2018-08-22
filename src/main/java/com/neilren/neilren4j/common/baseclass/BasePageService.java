package com.neilren.neilren4j.common.baseclass;

import com.neilren.neilren4j.common.conf.Neilren4jConfig;
import com.neilren.neilren4j.dao.TMenuMapper;
import com.neilren.neilren4j.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName BasePageService
 * @Description TODO
 * @Date 2018/7/18 14:40
 */
public class BasePageService extends BaseService {
    @Autowired
    protected TMenuMapper menuMapper;
    @Autowired
    protected Neilren4jConfig neilren4jConfig;
    @Autowired
    protected ArticleService articleService;
}
