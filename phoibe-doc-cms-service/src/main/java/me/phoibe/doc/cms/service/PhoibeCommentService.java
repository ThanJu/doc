package me.phoibe.doc.cms.service;

import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.domain.po.PhoibeComment;

/**
 * Created by carrey on 18-8-26.
 */
public interface PhoibeCommentService {

    void addComment(PhoibeComment phoibeComment);

    PageList<PhoibeComment> fetchCommentByPageList(PageParam<PhoibeComment> pageParam);
}
