package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeSystemProperties;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhoibeSystemPropertiesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhoibeSystemProperties record);

    int insertSelective(PhoibeSystemProperties record);

    PhoibeSystemProperties selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhoibeSystemProperties record);

    int updateByPrimaryKey(PhoibeSystemProperties record);
}