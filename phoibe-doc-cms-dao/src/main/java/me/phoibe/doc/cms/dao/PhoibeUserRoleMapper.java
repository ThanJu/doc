package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhoibeUserRoleMapper {
    int deleteByPrimaryKey(Long userRoleId);

    int insert(PhoibeUserRole record);

    int insertSelective(PhoibeUserRole record);

    PhoibeUserRole selectByPrimaryKey(Long userRoleId);

    int updateByPrimaryKeySelective(PhoibeUserRole record);

    int updateByPrimaryKey(PhoibeUserRole record);
}