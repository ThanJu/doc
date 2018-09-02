package me.phoibe.doc.cms.service.impl;

import me.phoibe.doc.cms.dao.PhoibeDocumentMapper;
import me.phoibe.doc.cms.domain.dto.DPhoebeDocument;
import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.service.PhoibeDocumentService;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    public PageList<DPhoebeDocument> fetchDocumentByPageList(PageParam<DPhoebeDocument> pageParam) {
        List<DPhoebeDocument> dlist = new ArrayList<>();
        List<PhoibeDocument> list = phoibeDocumentMapper.selectByPage(pageParam);
        for (PhoibeDocument model:list){
            DPhoebeDocument dmodel = new DPhoebeDocument();
            BeanUtils.copyProperties(model,dmodel);
            dmodel.settings();
            dlist.add(dmodel);
        }
        return PageList.createPage(pageParam,phoibeDocumentMapper.selectCountByPage(pageParam),dlist);
    }

    @Override
    public List<DPhoebeDocument> fetchDocumentUserList(PageParam<DPhoebeDocument> pageParam) {
        return phoibeDocumentMapper.selectDocumentUser(pageParam);
    }

    @Override
    public void removeDocumentById(Integer id) throws Exception {
        if(null == id){
            throw new Exception("删除参数id为空");
        }
        phoibeDocumentMapper.deleteByPrimaryKey(id.longValue());
    }

    @Override
    public void modifyDocumentById(PhoibeDocument phoibeDocument) throws Exception {
        if(null == phoibeDocument || null == phoibeDocument.getId()){
            throw new Exception("修改参数错误");
        }
        phoibeDocumentMapper.updateByPrimaryKeySelective(phoibeDocument);
    }

    @Override
    public DPhoebeDocument fetchDocumentById(Integer id) {
        if(null == id){
            return null;
        }
        DPhoebeDocument dmodel = new DPhoebeDocument();
        PhoibeDocument model = phoibeDocumentMapper.selectByPrimaryKey(id.longValue());
        if(null == model){
            return null;
        }
        BeanUtils.copyProperties(model,dmodel);
        dmodel.settings();
        return dmodel;
    }
}
