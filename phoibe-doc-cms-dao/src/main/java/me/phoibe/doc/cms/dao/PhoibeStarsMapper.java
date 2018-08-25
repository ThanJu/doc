package me.phoibe.doc.cms.dao;

import java.math.BigDecimal;
import java.util.List;
import me.phoibe.doc.cms.domain.po.PhoibeStars;
import me.phoibe.doc.cms.domain.po.PhoibeStarsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PhoibeStarsMapper {
    long countByExample(PhoibeStarsExample example);

    int deleteByExample(PhoibeStarsExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PhoibeStars record);

    int insertSelective(PhoibeStars record);

    List<PhoibeStars> selectByExample(PhoibeStarsExample example);

    PhoibeStars selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") PhoibeStars record, @Param("example") PhoibeStarsExample example);

    int updateByExample(@Param("record") PhoibeStars record, @Param("example") PhoibeStarsExample example);

    int updateByPrimaryKeySelective(PhoibeStars record);

    int updateByPrimaryKey(PhoibeStars record);
}