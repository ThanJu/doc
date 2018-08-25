package me.phoibe.doc.cms.domain.po;

/**
 * Created by carrey on 18-8-25.
 */
public class PageParam<T> {
    private Integer start;
    private Integer limit;
    private T param;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
