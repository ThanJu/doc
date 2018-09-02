package me.phoibe.doc.cms.domain.po;

import java.util.Date;

public class PhoibeDicvalues {
    private Short id;

    private Short itemid;

    private Object text;

    private Object value;

    private Short sequence;

    private Date createTime;

    private Short status;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getItemid() {
        return itemid;
    }

    public void setItemid(Short itemid) {
        this.itemid = itemid;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Short getSequence() {
        return sequence;
    }

    public void setSequence(Short sequence) {
        this.sequence = sequence;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}