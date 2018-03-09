package com.PT.bean.order;

public class OrderInfoBean {

    private String driver_name;
    private String project_type;
    private String project_descp;
    private int state;
    private String order_id;

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_descp() {
        return project_descp;
    }

    public void setProject_descp(String project_descp) {
        this.project_descp = project_descp;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
