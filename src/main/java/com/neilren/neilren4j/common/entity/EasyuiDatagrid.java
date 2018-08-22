package com.neilren.neilren4j.common.entity;

import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName EasyuiDatagrid
 * @Description TODO
 * @Date 2018/8/5 15:02
 */
public class EasyuiDatagrid<T> {
    private Long total;
    private List<T> rows;
    private List<T> footer;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public List<T> getFooter() {
        return footer;
    }

    public void setFooter(List<T> footer) {
        this.footer = footer;
    }
}
