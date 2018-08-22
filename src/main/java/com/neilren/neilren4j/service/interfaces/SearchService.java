package com.neilren.neilren4j.service.interfaces;

import com.neilren.neilren4j.entity.Search;

import java.util.List;

public interface SearchService {
    List<Search> search(String wd, int page, int rows);
}
