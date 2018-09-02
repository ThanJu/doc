package me.phoibe.doc.cms.domain.po;

import java.math.BigDecimal;
import java.util.Date;

public class PhoibeDocument {
    private Long id;

    private String name;

    private String format;

    private Long userId;

    private BigDecimal score;

    private String filePath;

    private BigDecimal fileSize;

    private Short combatType;

    private Short arms;

    private Short status;

    private Short auditStatus;

    private Long auditUserId;

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

    private String stocker;

    private String userRealName;

    private String waraddr;

    private Date wartime;

    private String winner;

    private String loser;

    private String warnum;

    private String warstate;

    private byte[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
        this.filePath = filePath == null ? null : filePath.trim();
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

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
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
        this.tag = tag == null ? null : tag.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public String getStocker() {
        return stocker;
    }

    public void setStocker(String stocker) {
        this.stocker = stocker == null ? null : stocker.trim();
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }

    public String getWaraddr() {
        return waraddr;
    }

    public void setWaraddr(String waraddr) {
        this.waraddr = waraddr == null ? null : waraddr.trim();
    }

    public Date getWartime() {
        return wartime;
    }

    public void setWartime(Date wartime) {
        this.wartime = wartime;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner == null ? null : winner.trim();
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser == null ? null : loser.trim();
    }

    public String getWarnum() {
        return warnum;
    }

    public void setWarnum(String warnum) {
        this.warnum = warnum == null ? null : warnum.trim();
    }

    public String getWarstate() {
        return warstate;
    }

    public void setWarstate(String warstate) {
        this.warstate = warstate == null ? null : warstate.trim();
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}