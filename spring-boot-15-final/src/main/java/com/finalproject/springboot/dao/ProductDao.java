package com.finalproject.springboot.dao;

import com.finalproject.springboot.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @program: mengxuegu
 * @description: 静态类  暂时用
 * @author: Lunatic Princess
 * @create: 2019-04-15 09:14
 **/
@Repository
public class ProductDao {
    private static Map<Integer, Product> productMap = null;
    private static Integer initId = 2007;

    static {
        productMap = new HashMap<Integer, Product>();

        productMap.put(2001, new Product(2001, "PD-01", "供应商111", "LUYAO-a7", "17000", 1, "1900万", "日本横滨县", 1, 1, "camera_1.jpg", 100));
        productMap.put(2002, new Product(2002, "PD-02", "供应商111", "LUYAO-a6", "17000", 1, "1900万", "日本横滨县", 1, 1, "camera_2.jpg", 100));
        productMap.put(2003, new Product(2003, "PD-03", "供应商111", "LUYAO-a5", "17000", 2, "1900万", "日本横滨县", 2, 1, "camera_3.jpg", 100));
        productMap.put(2004, new Product(2004, "PD-04", "供应商333", "LUYAO-a4", "17000", 2, "1900万", "日本横滨县", 2, 2, "camera_1.jpg", 100));
        productMap.put(2005, new Product(2005, "PD-05", "供应商222", "LUYAO-a3", "17000", 2, "1900万", "日本横滨县", 2, 2, "camera_2.jpg", 100));
        productMap.put(2006, new Product(2006, "PD-06", "供应商222", "LUYAO-a2", "17000", 1, "1900万", "日本横滨县", 1, 2, "camera_3.jpg", 100));
    }

    public void save(Product product){
        if (product.getCid() == null) {
            product.setCid(initId++);
        }
        product.setCreateDate(new Date());
        productMap.put(product.getCid(), product);
    }

    public Collection<Product> getAll() {
        return productMap.values();
    }

    public Collection<Product> getAll(String model) {
        Collection<Product> products = getAll();

        if (!StringUtils.isEmpty(model)) {
            int count = 0;
            for (Product product : products) {
                if (product.getModel().toUpperCase().contains(model.toUpperCase())) {
                    count++;
                    products = count > 1 ? products : new ArrayList<Product>();
                    products.add(product);
                }
                if (count == 0) {
                    products = new ArrayList<Product>();
                }
            }
        }
        return products;
    }

    public Product getProduct(Integer cid) {
        return productMap.get(cid);
    }

    public void delete(Integer cid) {
        productMap.remove(cid);
    }

}
