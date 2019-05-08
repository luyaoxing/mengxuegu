package com.finalproject.springboot.controller;

import com.finalproject.springboot.dao.ProductDao;
import com.finalproject.springboot.entity.Product;
import com.finalproject.springboot.entity.Provider;
import com.finalproject.springboot.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @program: mengxuegu
 * @description: 商场相机信息展示控制器
 * @author: Lunatic Princess
 * @create: 2019-04-15 09:53
 **/
@Controller
public class ProductController {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductMapper productMapper;

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/products")
    public String list(Map<String, Object> map, Product product) {
        logger.info("商品列表查询。。。" + product);

        List<Provider> products = productMapper.getProduct(product);
        map.put("products", products);

        return "product/list";
    }

    @GetMapping("/product/{cid}")
    public String view(@PathVariable("cid") Integer cid,
                       @RequestParam(value = "type", defaultValue = "view") String type,
                       Map<String, Object> map) {
        logger.info("查询cid= " + cid + " 的商品相机的详细情况。 ");

        Product products = productMapper.getProductByCid(cid);

        map.put("product", products);

        logger.info("success!");

        return "product/" + type;
    }

    //go to add page
    @GetMapping("/product")
    public String toAddPage() {
        //前往添加页面
        return "product/add";
    }

    //处理添加商品相机请求
    @PostMapping("/product")
    public String addProduct(Product product) {
        logger.info("添加商品相机信息。。。" + product);

        productMapper.addProduct(product);

        return "redirect:/products";
    }

    @PutMapping("/product")
    public String update(Product product) {
        logger.info("更改相机商品信息。。。");
        //更新操作
        productMapper.updateProduct(product);

        logger.info(" update success" + product);
        return "redirect:products";
    }

    //删除商品
    @GetMapping("/dproduct/{cid}")
    public String delete(@PathVariable("cid") Integer cid) {
        logger.info("删除cid= " + cid + " 的商品相机。");

        productMapper.deleteProductByCid(cid);

        return "redirect:/products";
    }
}
