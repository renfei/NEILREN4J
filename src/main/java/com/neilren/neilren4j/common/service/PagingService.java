package com.neilren.neilren4j.common.service;

import com.neilren.neilren4j.modules.article.entity.ArticlePaging;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页服务
 *
 * @author NeilRen
 * @version 1.0
 */
@Service
public class PagingService extends BaseService {

    /**
     * 分页服务
     *
     * @param index 当前页
     * @param total 总文章数
     * @return
     */
    public List<ArticlePaging> getPaging(int index, int total) {
        return getPaging(index, total, 10);
    }

    /**
     * 分页服务
     *
     * @param index 当前页
     * @param total 总文章数
     * @param size  分页大小
     * @return
     */
    public List<ArticlePaging> getPaging(int index, int total, int size) {
        List<ArticlePaging> articlePagingList = new ArrayList<ArticlePaging>();
        int front = 4;
        total = (int) Math.ceil(total / size);
        articlePagingList.add(new ArticlePaging("首页", 1));
        if (index > total - 3) {
            //后方不足数
            front = 6 - (total - index);
        }
        List<ArticlePaging> tempList = new ArrayList<ArticlePaging>();
        //从当前页往前取3个，但不能成负数
        for (int i = 0, j = index; i < front && j > 0; i++, j--) {
            tempList.add(new ArticlePaging(String.valueOf(j), j));
        }
        //顺序修正，需要倒序
        for (int i = tempList.size() - 1; i >= 0; i--) {
            articlePagingList.add(tempList.get(i));
        }
        //如果不满8个，就一直加，但是不能加的超过总页数
        for (int i = articlePagingList.size(), j = index + 1; i < 8 && j <= total; i++, j++) {
            articlePagingList.add(new ArticlePaging(String.valueOf(j), j));
        }
        articlePagingList.add(new ArticlePaging("末页", total));
        return articlePagingList;
    }
}
