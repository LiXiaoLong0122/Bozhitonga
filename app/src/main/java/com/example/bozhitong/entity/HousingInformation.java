package com.example.bozhitong.entity;

/**
 * Created by Administrator on 2017-05-03.
 * 房屋信息
 */

public class HousingInformation {
    private String name; //业主
    private String phone;
    private String address;//房屋地址
    private int status;//审核状态  1 审核过   0 未过
    private String community;//小区
    private String identity;//身份
   private boolean isdefault;//默认小区

    public boolean isdefault() {
        return isdefault;
    }

    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
