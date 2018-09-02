package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeDicvalues;

public interface PhoibeDicvaluesMapper {
    int deleteByPrimaryKey(Short id);

    int insert(PhoibeDicvalues record);

    int insertSelective(PhoibeDicvalues record);

    PhoibeDicvalues selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(PhoibeDicvalues record);

    int updateByPrimaryKey(PhoibeDicvalues record);
}