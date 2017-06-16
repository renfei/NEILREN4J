package com.neilren.neilren4j.modules.article.service;

import com.neilren.neilren4j.common.cache.memcached.MemcachedManager;
import com.neilren.neilren4j.common.config.Global;
import com.neilren.neilren4j.common.service.BaseService;
import com.neilren.neilren4j.modules.article.dao.FrielinkDao;
import com.neilren.neilren4j.modules.article.entity.FrielinkWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by neil on 14/06/2017.
 */
@Service
@Transactional(readOnly = true)
public class FrielinkService extends BaseService {
    private static String memcachedFrieLinkKey = "FrieLink";
    @Autowired
    private FrielinkDao frielinkDao;
    @Autowired
    private MemcachedManager memcachedManager;

    public List<FrielinkWithBLOBs> getAllFrieLink() {
        List<FrielinkWithBLOBs> frielinkWithBLOBsList = null;
        frielinkWithBLOBsList = (List<FrielinkWithBLOBs>) memcachedManager.get(memcachedFrieLinkKey);
        if (frielinkWithBLOBsList == null || frielinkWithBLOBsList.size() < 1) {
            frielinkWithBLOBsList = frielinkDao.selectForAll();
            memcachedManager.set(memcachedFrieLinkKey, frielinkWithBLOBsList, Global.MemcachedExpire);
        }
        return frielinkWithBLOBsList;
    }
}
