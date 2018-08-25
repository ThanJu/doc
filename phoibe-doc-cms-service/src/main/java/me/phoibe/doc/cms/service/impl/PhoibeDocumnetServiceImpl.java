package me.phoibe.doc.cms.service.impl;

import me.phoibe.doc.cms.dao.PhoibeDocumentMapper;
import me.phoibe.doc.cms.domain.dto.DPhoibeDocument;
import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.service.PhoibeDocumentService;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
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
    public PageList<DPhoibeDocument> fetchDocumentByPageList(PageParam<DPhoibeDocument> pageParam) {
        List<DPhoibeDocument> dlist = new ArrayList<>();
        List<PhoibeDocument> list = phoibeDocumentMapper.selectByPage(pageParam);
        for (PhoibeDocument model:list){
            DPhoibeDocument dmodel = new DPhoibeDocument();
            BeanUtils.copyProperties(model,dmodel);
            dmodel.settings();
            dlist.add(dmodel);
        }
        return new PageList<DPhoibeDocument>().createPage(pageParam.getStart(),pageParam.getLimit(),phoibeDocumentMapper.selectCountByPage(pageParam),dlist);
    }

    @Override
    public List<DPhoibeDocument> fetchDocumentUserList(PageParam<DPhoibeDocument> pageParam) {
        return phoibeDocumentMapper.selectDocumentUser(pageParam);
    }
}
