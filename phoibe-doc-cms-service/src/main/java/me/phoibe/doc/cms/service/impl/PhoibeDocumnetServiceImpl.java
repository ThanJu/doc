package me.phoibe.doc.cms.service.impl;

import me.phoibe.doc.cms.dao.PhoibeDocumentMapper;
import me.phoibe.doc.cms.service.PhoibeDocumentService;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author pc
 * @Title: PhoibeDocumnetServiceImpl
 * @Description: TODO
 * @date 2018/8/230:31
 */
@Service
public class PhoibeDocumnetServiceImpl implements PhoibeDocumentService {
    @Resource
    private PhoibeDocumentMapper phoibeDocumentMapper;

    @Override
    public Integer save(PhoibeDocument phoibeDocument) {
        return phoibeDocumentMapper.insert(phoibeDocument);
    }
}
