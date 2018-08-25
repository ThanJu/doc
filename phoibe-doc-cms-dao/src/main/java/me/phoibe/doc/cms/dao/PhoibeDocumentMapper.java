package me.phoibe.doc.cms.dao;

import me.phoibe.doc.cms.domain.dto.DPhoibeDocument;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import me.phoibe.doc.cms.domain.po.PhoibeDocumentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface PhoibeDocumentMapper {
    long countByExample(PhoibeDocumentExample example);

    int deleteByExample(PhoibeDocumentExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PhoibeDocument record);

    int insertSelective(PhoibeDocument record);

    List<PhoibeDocument> selectByExampleWithBLOBs(PhoibeDocumentExample example);

    List<PhoibeDocument> selectByExample(PhoibeDocumentExample example);

    List<PhoibeDocument> selectByPage(PageParam<DPhoibeDocument> pageParam);

    Long selectCountByPage(PageParam<DPhoibeDocument> pageParam);

    PhoibeDocument selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") PhoibeDocument record, @Param("example") PhoibeDocumentExample example);

    int updateByExampleWithBLOBs(@Param("record") PhoibeDocument record, @Param("example") PhoibeDocumentExample example);

    int updateByExample(@Param("record") PhoibeDocument record, @Param("example") PhoibeDocumentExample example);

    int updateByPrimaryKeySelective(PhoibeDocument record);

    int updateByPrimaryKeyWithBLOBs(PhoibeDocument record);

    int updateByPrimaryKey(PhoibeDocument record);

    List<DPhoibeDocument> selectDocumentUser(PageParam<DPhoibeDocument> pageParam);
}