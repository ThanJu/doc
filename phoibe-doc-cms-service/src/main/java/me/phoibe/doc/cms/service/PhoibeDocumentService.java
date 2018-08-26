package me.phoibe.doc.cms.service;

import me.phoibe.doc.cms.domain.dto.DPhoibeDocument;
import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import me.phoibe.doc.cms.domain.po.PageParam;

import java.util.List;

/**
 * @author pc
 * @Title: PhoibeDocumnetService
 * @Description: 文档管理Service
 * @date 2018/8/230:30
 */
public interface PhoibeDocumentService {

    Integer save(PhoibeDocument phoibeDocument);

    PageList<DPhoibeDocument> fetchDocumentByPageList(PageParam<DPhoibeDocument> pageParam);

    List<DPhoibeDocument> fetchDocumentUserList(PageParam<DPhoibeDocument> pageParam);

    void removeDocumentById(Integer id) throws Exception;

    void modifyDocumentById(PhoibeDocument phoibeDocument) throws Exception;
}
