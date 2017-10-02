package com.neilren.neilren4j.modules.api.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by neil on 10/07/2017.
 */
public class PhilisenseKaoQin implements Serializable {
    private List<PhilisenseKaoQins> rows;

    public List<PhilisenseKaoQins> getRows() {
        return rows;
    }

    public void setRows(List<PhilisenseKaoQins> rows) {
        this.rows = rows;
    }
}
