package me.phoibe.doc.cms.service;

import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import me.phoibe.doc.cms.domain.po.PageParam;

/**
 * @author pc
 * @Title: PhoibeDocumnetService
 * @Description: 文档管理Service
 * @date 2018/8/230:30
 */
public interface PhoibeDocumentService {

    Integer save(PhoibeDocument phoibeDocument);

    PageList<PhoibeDocument> fetchDocumentByPageList(PageParam<PhoibeDocument> pageParam);
}
