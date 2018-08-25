package me.phoibe.doc.cms.dao;

import java.math.BigDecimal;
import java.util.List;
import me.phoibe.doc.cms.domain.po.PhoibeSystemProperties;
import me.phoibe.doc.cms.domain.po.PhoibeSystemPropertiesExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PhoibeSystemPropertiesMapper {
    long countByExample(PhoibeSystemPropertiesExample example);

    int deleteByExample(PhoibeSystemPropertiesExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PhoibeSystemProperties record);

    int insertSelective(PhoibeSystemProperties record);

    List<PhoibeSystemProperties> selectByExample(PhoibeSystemPropertiesExample example);

    PhoibeSystemProperties selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") PhoibeSystemProperties record, @Param("example") PhoibeSystemPropertiesExample example);

    int updateByExample(@Param("record") PhoibeSystemProperties record, @Param("example") PhoibeSystemPropertiesExample example);

    int updateByPrimaryKeySelective(PhoibeSystemProperties record);

    int updateByPrimaryKey(PhoibeSystemProperties record);
}