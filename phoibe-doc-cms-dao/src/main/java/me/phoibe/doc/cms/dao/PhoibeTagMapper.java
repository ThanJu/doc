package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeTag;

public interface PhoibeTagMapper {
    int deleteByPrimaryKey(Short id);

    int insert(PhoibeTag record);

    int insertSelective(PhoibeTag record);

    PhoibeTag selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(PhoibeTag record);

    int updateByPrimaryKey(PhoibeTag record);
}