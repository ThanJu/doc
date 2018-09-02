package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeStars;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhoibeStarsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhoibeStars record);

    int insertSelective(PhoibeStars record);

    PhoibeStars selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhoibeStars record);

    int updateByPrimaryKey(PhoibeStars record);
}