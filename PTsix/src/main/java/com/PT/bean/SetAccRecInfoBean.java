package com.PT.bean;

import java.util.Date;


public class SetAccRecInfoBean {
    private String setAccId;
    private String companyName;
    private String driverName;
    private String driverPhone;
    private int tradeMoney;
    private Date tradeAt;
    int state;

    public void print() {
        System.out.println(
                "\nsetAccId:    " + setAccId +
                "\ncompanyName: " + companyName +
                "\ndriverName:  " + driverName +
                "\ndriverPhone: " + driverPhone +
                "\ntradeMoney:  " + tradeMoney +
                "\ntradeMoney:  " + tradeAt +
                "\ntradeAt:     " + state
        );
    }
    public String getSetAccId() {
        return setAccId;
    }

    public void setSetAccId(String setAccId) {
        this.setAccId = setAccId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public int getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(int tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public Date getTradeAt() {
        return tradeAt;
    }

    public void setTradeAt(Date tradeAt) {
        this.tradeAt = tradeAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
