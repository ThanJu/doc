package me.phoibe.doc.cms.dao;

import java.util.List;
import me.phoibe.doc.cms.domain.po.PhoibeOperateLog;
import me.phoibe.doc.cms.domain.po.PhoibeOperateLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PhoibeOperateLogMapper {
    long countByExample(PhoibeOperateLogExample example);

    int deleteByExample(PhoibeOperateLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PhoibeOperateLog record);

    int insertSelective(PhoibeOperateLog record);

    List<PhoibeOperateLog> selectByExample(PhoibeOperateLogExample example);

    PhoibeOperateLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PhoibeOperateLog record, @Param("example") PhoibeOperateLogExample example);

    int updateByExample(@Param("record") PhoibeOperateLog record, @Param("example") PhoibeOperateLogExample example);

    int updateByPrimaryKeySelective(PhoibeOperateLog record);

    int updateByPrimaryKey(PhoibeOperateLog record);
}