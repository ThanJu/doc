package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeDicitem;

public interface PhoibeDicitemMapper {
    int deleteByPrimaryKey(Short id);

    int insert(PhoibeDicitem record);

    int insertSelective(PhoibeDicitem record);

    PhoibeDicitem selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(PhoibeDicitem record);

    int updateByPrimaryKey(PhoibeDicitem record);
}