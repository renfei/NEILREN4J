package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.modules.article.dao.CategoryDao;
import com.neilren.neilren4j.modules.article.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by neil on 28/06/2017.
 */
@Service
@Transactional(readOnly = true)
public class CategoryService extends BaseService {
    private static String memcachedCatAllKey = "CatAllKey";
    @Autowired
    private MemcachedManager memcachedManager;
    @Autowired
    private CategoryDao categoryDao;

    /**
     * 获取所有文章分类
     *
     * @return 分类List
     */
    public List<Category> getAllCat() {
        List<Category> catList = null;
        catList = (List<Category>) memcachedManager.get(memcachedCatAllKey);
        if (catList == null) {
            catList = categoryDao.selectAllCat();
            memcachedManager.set(memcachedCatAllKey, catList, Global.MemcachedExpire);
        }
        return catList;
    }
}
