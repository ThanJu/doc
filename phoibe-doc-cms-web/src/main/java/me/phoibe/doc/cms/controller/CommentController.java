package me.phoibe.doc.cms.controller;

import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.domain.po.PhoibeComment;
import me.phoibe.doc.cms.entity.Code;
import me.phoibe.doc.cms.entity.Result;
import me.phoibe.doc.cms.service.PhoibeCommentService;
import me.phoibe.doc.cms.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by carrey on 18-8-26.
 */
@Controller
@RequestMapping("phoibe/coment")
public class CommentController {
    @Autowired
    private PhoibeCommentService phoibeCommentService;

    @RequestMapping("save")
    public String save(@ModelAttribute PhoibeComment phoibeComment){
        phoibeComment.setUserId(1l);
        phoibeCommentService.addComment(phoibeComment);
        return JsonUtils.toJson(new Result<>(Code.SUCCESS, ""));
    }

    @RequestMapping("list/{docId}/{start}/{limit}")
    public String listComment(@PathVariable Integer start,@PathVariable Integer limit,@PathVariable Long docId){
        PageParam<PhoibeComment> pageParam = new PageParam<>();
        pageParam.setStart(start);
        pageParam.setLimit(limit);
        pageParam.setOrderBy("UPDATE_TIME");
        pageParam.setSort("DESC");
        PhoibeComment phoibeComment = new PhoibeComment();
        phoibeComment.setDocumentId(docId);
        pageParam.setParam(phoibeComment);
        PageList<PhoibeComment> pageList = phoibeCommentService.fetchCommentByPageList(pageParam);
        return JsonUtils.toJson(new Result<PageList<PhoibeComment>>(Code.SUCCESS, pageList));
    }
}
