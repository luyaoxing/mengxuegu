package com.mengxuegu.springboot.entities;

/**
 * @program: spring-boot-10-bill
 * @description: 继承的属性实体，连表查询    封装新的实体，因为账单列表要有供应商名称
 * @author: Lunatic Princess
 * @create: 2019-02-22
 * Detailed time at 15:18
 **/

public class BillProvider extends Bill {

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    private String providerName;
}
