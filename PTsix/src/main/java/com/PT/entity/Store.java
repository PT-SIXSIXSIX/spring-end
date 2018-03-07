package com.PT.entity;

public class Store {
    private Integer id;

    private String companyName;

    private String idCard;

    private String picHeadUrl;

    private String picTailUrl;

    private String serviceType;

    private String location;

    private Integer userId;

    private Integer deposit;

    private String reservePhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getPicHeadUrl() {
        return picHeadUrl;
    }

    public void setPicHeadUrl(String picHeadUrl) {
        this.picHeadUrl = picHeadUrl == null ? null : picHeadUrl.trim();
    }

    public String getPicTailUrl() {
        return picTailUrl;
    }

    public void setPicTailUrl(String picTailUrl) {
        this.picTailUrl = picTailUrl == null ? null : picTailUrl.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getReservePhone() {
        return reservePhone;
    }

    public void setReservePhone(String reservePhone) {
        this.reservePhone = reservePhone == null ? null : reservePhone.trim();
    }
}