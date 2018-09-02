package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PhoibeOperateLog;

public interface PhoibeOperateLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhoibeOperateLog record);

    int insertSelective(PhoibeOperateLog record);

    PhoibeOperateLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhoibeOperateLog record);

    int updateByPrimaryKey(PhoibeOperateLog record);
}