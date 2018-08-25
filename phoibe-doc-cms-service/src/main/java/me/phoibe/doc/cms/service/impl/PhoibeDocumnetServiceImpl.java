package me.phoibe.doc.cms.service.impl;

import me.phoibe.doc.cms.dao.PhoibeDocumentMapper;
import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.service.PhoibeDocumentService;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PageList<PhoibeDocument> fetchDocumentByPageList(PageParam<PhoibeDocument> pageParam) {
        List<PhoibeDocument> list = phoibeDocumentMapper.selectByPage(pageParam);
        return new PageList<PhoibeDocument>().createPage(pageParam.getStart(),pageParam.getLimit(),phoibeDocumentMapper.selectCountByPage(pageParam),list);
    }
}
