package me.phoibe.doc.cms.domain.po;

import java.util.List;

/**
 * Created by carrey on 18-8-25.
 */
public class PageList<T> {
    private Integer start;
    private Integer limit;
    private Long totalCount;
    private Integer pageNumber;
    private Integer pageCount;
    private List<T> dataList;

    public Integer getStart() {
        return start;
    }

    public Integer getLimit() {
        return limit;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public Integer getPageNumber(){
        return pageNumber;
    }

    public Integer getPageCount(){
        return pageCount;
    }

    public PageList<T> createPage(Integer start,Integer limit,Long totalCount,List<T> dataList){
        this.start = start;
        this.limit = limit;
        this.totalCount = totalCount;
        this.dataList = dataList;
        this.pageNumber = (start / limit)+1;
        this.pageCount = (int)(totalCount % limit.longValue())==0?(int)(totalCount / limit.longValue()):(int)(totalCount / limit.longValue())+1;
        return this;
    }
}
