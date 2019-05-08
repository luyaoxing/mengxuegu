package com.finalproject.springboot.mapper;

import com.finalproject.springboot.entity.Product;
import com.finalproject.springboot.entity.Provider;

import java.util.List;

/**
 * @program: mengxuegu
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-04-29 09:58
 **/
public interface ProductMapper {

    List<Provider> getProduct(Product product);

    Product getProductByCid(Integer cid);

    int addProduct(Product product);

    int deleteProductByCid(Integer cid);

    int updateProduct(Product product);
}
