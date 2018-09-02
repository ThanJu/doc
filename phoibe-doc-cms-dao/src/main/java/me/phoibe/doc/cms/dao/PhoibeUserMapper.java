package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeUser;

public interface PhoibeUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhoibeUser record);

    int insertSelective(PhoibeUser record);

    PhoibeUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhoibeUser record);

    int updateByPrimaryKey(PhoibeUser record);
}