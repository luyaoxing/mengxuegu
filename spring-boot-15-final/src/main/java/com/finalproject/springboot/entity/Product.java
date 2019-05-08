package com.finalproject.springboot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: mengxuegu
 * @description: 商城展现的所有相机实体
 * @author: Lunatic Princess
 * @create: 2019-04-15 08:56
 **/
public class Product implements Serializable {

    private Integer cid;                    //唯一主键
    private String productCode;             //商品编码
    private String providerName;            //供应商名称
    private String model;                   //相机型号
    private String price;                   //价格
    private Integer sensor;
    //传感器类型‘1 or 2’   注：数码相机传感器类型主要有两	种类型：CMOS 和 CCD
    private String pixel;                   //像素
    private String address;                 //生产地
    private Integer antiShakeMechanism;
    //防抖机制‘1 or 2’int  注：1是光学防抖；2是电子防抖
    private Integer manipulationMode;
    //操控方式‘1 or 2’int  注：1是电子触控；2是按键压控
    private String photo;                   //相机图片
    private Date createDate;                //创建时间
    private Integer stock;                  //剩余库存

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getSensor() {
        return sensor;
    }

    public void setSensor(Integer sensor) {
        this.sensor = sensor;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAntiShakeMechanism() {
        return antiShakeMechanism;
    }

    public void setAntiShakeMechanism(Integer antiShakeMechanism) {
        this.antiShakeMechanism = antiShakeMechanism;
    }

    public Integer getManipulationMode() {
        return manipulationMode;
    }

    public void setManipulationMode(Integer manipulationMode) {
        this.manipulationMode = manipulationMode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "cid=" + cid +
                ", productCode='" + productCode + '\'' +
                ", providerName='" + providerName + '\'' +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                ", sensor=" + sensor +
                ", pixel='" + pixel + '\'' +
                ", address='" + address + '\'' +
                ", antiShakeMechanism=" + antiShakeMechanism +
                ", manipulationMode=" + manipulationMode +
                ", photo='" + photo + '\'' +
                ", createDate=" + createDate +
                ", stock=" + stock +
                '}';
    }

    public Product() {
    }

    public Product(Integer cid, String productCode, String providerName, String model, String price, Integer sensor, String pixel, String address, Integer antiShakeMechanism, Integer manipulationMode, String photo, Integer stock) {
        this.cid = cid;
        this.productCode = productCode;
        this.providerName = providerName;
        this.model = model;
        this.price = price;
        this.sensor = sensor;
        this.pixel = pixel;
        this.address = address;
        this.antiShakeMechanism = antiShakeMechanism;
        this.manipulationMode = manipulationMode;
        this.photo = photo;
        this.stock = stock;
        this.createDate = createDate;
    }
}
