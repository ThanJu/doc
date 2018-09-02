package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeSystemProperties;

public interface PhoibeSystemPropertiesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhoibeSystemProperties record);

    int insertSelective(PhoibeSystemProperties record);

    PhoibeSystemProperties selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhoibeSystemProperties record);

    int updateByPrimaryKey(PhoibeSystemProperties record);
}