package me.phoibe.doc.cms.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DPhoibeDocument {
    private BigDecimal id;

    private String name;

    private String format;

    private BigDecimal userId;

    private BigDecimal score;

    private String filePath;

    private BigDecimal fileSize;

    private Short combatType;

    private String combatTypeDesc;

    private Short arms;

    private String armsDesc;

    private Short status;

    private String statusDesc;

    private Short auditStatus;

    private String auditStatusDesc;

    private BigDecimal auditUserId;

    private Short progress;

    private String tag;

    private String description;

    private Date createTime;

    private Date updateTime;

    private Short pagecount;

    private Short hitcount;

    private Short recorder;

    private Date auditTime;

    private Short isstock;

    private Date stockTime;

    private Date auditTimeBegin;

    private Date auditTimeEnd;

    private String userRealName;

    public void settings(){

    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getCombatTypeDesc() {
        return combatTypeDesc;
    }

    public void setCombatTypeDesc(String combatTypeDesc) {
        this.combatTypeDesc = combatTypeDesc;
    }

    public String getArmsDesc() {
        return armsDesc;
    }

    public void setArmsDesc(String armsDesc) {
        this.armsDesc = armsDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getAuditStatusDesc() {
        return auditStatusDesc;
    }

    public void setAuditStatusDesc(String auditStatusDesc) {
        this.auditStatusDesc = auditStatusDesc;
    }

    public Date getAuditTimeBegin() {
        return auditTimeBegin;
    }

    public void setAuditTimeBegin(Date auditTimeBegin) {
        this.auditTimeBegin = auditTimeBegin;
    }

    public Date getAuditTimeEnd() {
        return auditTimeEnd;
    }

    public void setAuditTimeEnd(Date auditTimeEnd) {
        this.auditTimeEnd = auditTimeEnd;
    }

    public Short getPagecount() {
        return pagecount;
    }

    public void setPagecount(Short pagecount) {
        this.pagecount = pagecount;
    }

    public Short getHitcount() {
        return hitcount;
    }

    public void setHitcount(Short hitcount) {
        this.hitcount = hitcount;
    }

    public Short getRecorder() {
        return recorder;
    }

    public void setRecorder(Short recorder) {
        this.recorder = recorder;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Short getIsstock() {
        return isstock;
    }

    public void setIsstock(Short isstock) {
        this.isstock = isstock;
    }

    public Date getStockTime() {
        return stockTime;
    }

    public void setStockTime(Date stockTime) {
        this.stockTime = stockTime;
    }

    private byte[] content;

    private String userName;

    private Short type;

    private String realname;

    private String nickname;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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