package com.PT.entity;

import java.util.Date;

public class SettleAccRecord {
    private Integer id;

    private Integer setAccId;

    private Integer state;

    private Integer revMonId;

    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSetAccId() {
        return setAccId;
    }

    public void setSetAccId(Integer setAccId) {
        this.setAccId = setAccId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRevMonId() {
        return revMonId;
    }

    public void setRevMonId(Integer revMonId) {
        this.revMonId = revMonId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}