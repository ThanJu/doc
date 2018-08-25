package me.phoibe.doc.cms.domain.po;

import java.math.BigDecimal;
import java.util.Date;

public class PhoibeDocument {
    private BigDecimal id;

    private String name;

    private String format;

    private BigDecimal userId;

    private BigDecimal score;

    private String filePath;

    private BigDecimal fileSize;

    private Short combatType;

    private Short arms;

    private Short status;

    private Short auditStatus;

    private BigDecimal auditUserId;

    private Short progress;

    private String tag;

    private String description;

    private Date createTime;

    private Date updateTime;

    private byte[] content;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public Short getCombatType() {
        return combatType;
    }

    public void setCombatType(Short combatType) {
        this.combatType = combatType;
    }

    public Short getArms() {
        return arms;
    }

    public void setArms(Short arms) {
        this.arms = arms;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Short auditStatus) {
        this.auditStatus = auditStatus;
    }

    public BigDecimal getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(BigDecimal auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Short getProgress() {
        return progress;
    }

    public void setProgress(Short progress) {
        this.progress = progress;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}