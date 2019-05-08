package com.finalproject.springboot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: mengxuegu
 * @description: 供应商
 * @author: Lunatic Princess
 * @create: 2019-04-10 13:16
 **/
public class Provider implements Serializable {
    private Integer pid;
    //供应商编码
    private String providerCode;
    //供应商名称
    private String providerName;
    //联系人
    private String people;
    //联系电话
    private String phone;
    //联系地址
    private String address;
    //传真
    private String fax;
    //描述
    private String describe;
    // 创建时间
    private Date createDate;
    //供应商logo
    private String photo;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "pid=" + pid +
                ", providerCode='" + providerCode + '\'' +
                ", providerName='" + providerName + '\'' +
                ", people='" + people + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", fax='" + fax + '\'' +
                ", describe='" + describe + '\'' +
                ", createDate=" + createDate +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Provider() {
    }

    public Provider(Integer pid, String providerCode, String providerName, String people, String phone, String address, String fax, String describe, String photo) {
        this.pid = pid;
        this.providerCode = providerCode;
        this.providerName = providerName;
        this.people = people;
        this.phone = phone;
        this.address = address;
        this.fax = fax;
        this.describe = describe;
        this.createDate = createDate;
        this.photo = photo;
    }
}

