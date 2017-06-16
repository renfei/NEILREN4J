package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.modules.article.dao.TagDao;
import com.neilren.neilren4j.modules.article.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标签Service
 * Created by neil on 13/06/2017.
 */
@Service
@Transactional(readOnly = true)
public class TagService extends BaseService {

    private static String memcachedTagAllKey = "Tag_all";
    @Autowired
    private TagDao tagDao;
    @Autowired
    private MemcachedManager memcachedManager;

    /**
     * 获取所有文章标签
     * @return 标签List
     */
    public List<Tag> getAllTag() {
        List<Tag> tagList = null;
        tagList = (List<Tag>) memcachedManager.get(memcachedTagAllKey);
        if (tagList == null) {
            tagList = tagDao.selectAllTag();
            memcachedManager.set(memcachedTagAllKey, tagList, Global.MemcachedExpire);
        }
        return tagList;
    }
}
