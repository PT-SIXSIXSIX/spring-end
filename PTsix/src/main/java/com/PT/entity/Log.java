package com.PT.entity;

import java.util.Date;

public class Log {
    private Integer id;

    private Integer optUsrId;

    private String optType;

    private String optDescp;

    private Date operationAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOptUsrId() {
        return optUsrId;
    }

    public void setOptUsrId(Integer optUsrId) {
        this.optUsrId = optUsrId;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    public String getOptDescp() {
        return optDescp;
    }

    public void setOptDescp(String optDescp) {
        this.optDescp = optDescp == null ? null : optDescp.trim();
    }

    public Date getOperationAt() {
        return operationAt;
    }

    public void setOperationAt(Date operationAt) {
        this.operationAt = operationAt;
    }
}