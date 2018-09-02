package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.dto.DPhoebeDocument;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhoibeDocumentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhoibeDocument record);

    int insertSelective(PhoibeDocument record);

    PhoibeDocument selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhoibeDocument record);

    int updateByPrimaryKeyWithBLOBs(PhoibeDocument record);

    int updateByPrimaryKey(PhoibeDocument record);

    List<PhoibeDocument> selectByPage(PageParam<DPhoebeDocument> pageParam);

    Long selectCountByPage(PageParam<DPhoebeDocument> pageParam);

    List<DPhoebeDocument> selectDocumentUser(PageParam<DPhoebeDocument> pageParam);
}