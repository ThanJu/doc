package me.phoibe.doc.cms.domain.dto;

import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

public class DPhoebeDocument extends PhoibeDocument {

    private String combatTypeDesc;

    private String armsDesc;

    private String statusDesc;

    private Short auditStatus;

    private String auditStatusDesc;

    private Date auditTimeBegin;

    private Date auditTimeEnd;

    private String userRealName;

    private String stocker;

    private Date stockTimeBegin;

    private Date stockTimeEnd;

    private String contentStr;

    public void settings(){
        if(!StringUtils.isEmpty(getContent())){
            this.contentStr =new String(getContent());
            setContent(null);
        }
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }

    public String getStocker() {
        return stocker;
    }

    public void setStocker(String stocker) {
        this.stocker = stocker;
    }

    public Date getStockTimeBegin() {
        return stockTimeBegin;
    }

    public void setStockTimeBegin(Date stockTimeBegin) {
        this.stockTimeBegin = stockTimeBegin;
    }

    public Date getStockTimeEnd() {
        return stockTimeEnd;
    }

    public void setStockTimeEnd(Date stockTimeEnd) {
        this.stockTimeEnd = stockTimeEnd;
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

    public Short getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Short auditStatus) {
        this.auditStatus = auditStatus;
    }

}