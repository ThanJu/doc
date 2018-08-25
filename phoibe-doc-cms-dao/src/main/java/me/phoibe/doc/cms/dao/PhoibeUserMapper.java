package me.phoibe.doc.cms.dao;

import java.util.List;
import me.phoibe.doc.cms.domain.po.PhoibeUser;
import me.phoibe.doc.cms.domain.po.PhoibeUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PhoibeUserMapper {
    long countByExample(PhoibeUserExample example);

    int deleteByExample(PhoibeUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PhoibeUser record);

    int insertSelective(PhoibeUser record);

    List<PhoibeUser> selectByExample(PhoibeUserExample example);

    PhoibeUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PhoibeUser record, @Param("example") PhoibeUserExample example);

    int updateByExample(@Param("record") PhoibeUser record, @Param("example") PhoibeUserExample example);

    int updateByPrimaryKeySelective(PhoibeUser record);

    int updateByPrimaryKey(PhoibeUser record);
}