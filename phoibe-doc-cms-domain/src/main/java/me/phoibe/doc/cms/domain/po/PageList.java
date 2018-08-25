package me.phoibe.doc.cms.domain.po;

import java.util.List;

/**
 * Created by carrey on 18-8-25.
 */
public class PageList<T> {
    private Integer start;
    private Integer limit;
    private Long totalCount;
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
        return (start / limit)+1;
    }

    public Integer getPageCount(){
        return (int)(totalCount % limit.longValue())==0?(int)(totalCount / limit.longValue()):(int)(totalCount / limit.longValue())+1;
    }

    public PageList<T> createPage(Integer start,Integer limit,Long totalCount,List<T> dataList){
        this.start = start;
        this.limit = limit;
        this.totalCount = totalCount;
        this.dataList = dataList;
        return this;
    }
}
