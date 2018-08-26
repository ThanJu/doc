package me.phoibe.doc.cms.service.impl;

import me.phoibe.doc.cms.dao.PhoibeCommentMapper;
import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.domain.po.PhoibeComment;
import me.phoibe.doc.cms.service.PhoibeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by carrey on 18-8-26.
 */
@Service
public class PhoibeCommentServiceImpl implements PhoibeCommentService {

    @Autowired
    private PhoibeCommentMapper phoibeCommentMapper;

    @Override
    public void addComment(PhoibeComment phoibeComment) {
        phoibeComment.setCreateTime(new Date());
        phoibeComment.setUpdateTime(phoibeComment.getCreateTime());
        phoibeCommentMapper.insertSelective(phoibeComment);
    }

    @Override
    public PageList<PhoibeComment> fetchCommentByPageList(PageParam<PhoibeComment> pageParam) {
        List<PhoibeComment> list = phoibeCommentMapper.selectByPage(pageParam);

        return new PageList<PhoibeComment>().createPage(pageParam.getStart(),pageParam.getLimit(),phoibeCommentMapper.selectCountByPage(pageParam),list);
    }
}
