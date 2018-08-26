package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.domain.po.PhoibeComment;
import me.phoibe.doc.cms.domain.po.PhoibeCommentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PhoibeCommentMapper {
    long countByExample(PhoibeCommentExample example);

    int deleteByExample(PhoibeCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PhoibeComment record);

    int insertSelective(PhoibeComment record);

    List<PhoibeComment> selectByExample(PhoibeCommentExample example);

    PhoibeComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PhoibeComment record, @Param("example") PhoibeCommentExample example);

    int updateByExample(@Param("record") PhoibeComment record, @Param("example") PhoibeCommentExample example);

    int updateByPrimaryKeySelective(PhoibeComment record);

    int updateByPrimaryKey(PhoibeComment record);

    List<PhoibeComment> selectByPage(PageParam<PhoibeComment> pageParam);

    Long selectCountByPage(PageParam<PhoibeComment> pageParam);
}