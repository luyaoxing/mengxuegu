package com.finalproject.springboot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: mengxuegu
 * @description: 商城所有用户实体属性
 * @author: Lunatic Princess
 * @create: 2019-04-09 21:17
 **/
public class User implements Serializable {

    private Integer id;

    //用户名
    private String username;
    //真实姓名
    private String realName;
    //用户密码
    private String password;
    //性别：1 女  2 男
    private Integer gender;
    //生日
    private Date birthday;
    //1管理员  2经理  3普通用户
    private Integer userType;

    public User() {
    }

    public User(String username, Integer gender) {
        this.username = username;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public User(Integer id, String username, String realName, String password, Integer gender, Integer userType) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.gender = gender;
        this.birthday = new Date();
        this.userType = userType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", userType=" + userType +
                '}';
    }
}