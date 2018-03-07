package com.PT.entity;

import java.util.Date;

public class SettleAccRecord {
    private Integer id;

    private String setAccId;

    private Integer tradeMoney;

    private Integer status;

    private Date tradedAt;

    private Date createdAt;

    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetAccId() {
        return setAccId;
    }

    public void setSetAccId(String setAccId) {
        this.setAccId = setAccId == null ? null : setAccId.trim();
    }

    public Integer getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(Integer tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTradedAt() {
        return tradedAt;
    }

    public void setTradedAt(Date tradedAt) {
        this.tradedAt = tradedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}