package com.neilren.neilren4j.dao;

import com.neilren.neilren4j.dbentity.TLogSendEmail;
import java.util.List;

public interface TLogSendEmailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TLogSendEmail record);

    TLogSendEmail selectByPrimaryKey(Long id);

    List<TLogSendEmail> selectAll();

    int updateByPrimaryKey(TLogSendEmail record);
}