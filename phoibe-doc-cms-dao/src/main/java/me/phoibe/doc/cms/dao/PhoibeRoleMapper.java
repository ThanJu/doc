package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhoibeRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(PhoibeRole record);

    int insertSelective(PhoibeRole record);

    PhoibeRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(PhoibeRole record);

    int updateByPrimaryKey(PhoibeRole record);

    PhoibeRole selectByUserId(Long userId);
}