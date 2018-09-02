package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeDicvalues;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhoibeDicvaluesMapper {
    int deleteByPrimaryKey(Short id);

    int insert(PhoibeDicvalues record);

    int insertSelective(PhoibeDicvalues record);

    PhoibeDicvalues selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(PhoibeDicvalues record);

    int updateByPrimaryKey(PhoibeDicvalues record);
}