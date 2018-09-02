package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.domain.po.PhoibeComment;

import java.util.List;

public interface PhoibeCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhoibeComment record);

    int insertSelective(PhoibeComment record);

    PhoibeComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhoibeComment record);

    int updateByPrimaryKey(PhoibeComment record);

    List<PhoibeComment> selectByPage(PageParam<PhoibeComment> pageParam);

    Long selectCountByPage(PageParam<PhoibeComment> pageParam);
}