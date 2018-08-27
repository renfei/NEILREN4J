package com.neilren.neilren4j.entity.aliopenserach;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName Results
 * @Description TODO
 * @Date 2018/7/27 16:16
 */
@Data
public class Results implements Serializable {
    private String searchtime;
    private int total;
    private int num;
    private int viewtotal;
    private List<ResultsItems> items;
}
