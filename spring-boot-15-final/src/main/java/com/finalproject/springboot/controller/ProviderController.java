package com.finalproject.springboot.controller;

import com.finalproject.springboot.dao.ProviderDao;
import com.finalproject.springboot.entity.Provider;
import com.finalproject.springboot.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: mengxuegu
 * @description: 相机商城供应商
 * @author: Lunatic Princess
 * @create: 2019-04-10 12:43
 *
 * type = null 进入查看详情页面view.html
 * type = update 则是进入update.html
 **/
@Controller
public class ProviderController {

    @Autowired
    ProviderDao providerDao;

    @Autowired
    ProviderMapper providerMapper;

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/providers")                                                      //不是必须传参
    public String list(Map<String, Object> map, Provider provider){

        logger.info("供应商列表查询。。。" + provider);

        List<Provider> providers = providerMapper.getProvider(provider);

        map.put("providers", providers);
        map.put("providerName", provider.getProviderName());

        return "provider/list";
    }

    @GetMapping("/provider/{pid}")
    public String view(@PathVariable("pid") Integer pid,
                       @RequestParam(value = "type", defaultValue = "view") String type,
                       Map<String, Object> map) {
        logger.info("查询 " + pid + " 的供应商的详细信息");

        Provider provider = providerMapper.getProviderByPid(pid);
        map.put("provider", provider);
        System.out.println(provider);

        return "provider/" + type;
    }

    //修改供应商信息
    @PutMapping("/provider")
    public String update(Provider provider){
        logger.info("更改供应商信息。。。");
        //更新操作
        providerMapper.updateProvider(provider);

        return "redirect:providers";
    }

    //前往添加供应商页面
    @GetMapping("/provider")
    public String toAddPage() {
        //前往添加供应商页面
        return "provider/add";
    }

    //处理添加供应商请求
    @PostMapping("/provider")
    public String addProvider(Provider provider) {
        logger.info("添加供应商信息：" + provider);

        provider.setCreateDate(new Date());
        providerMapper.addProvider(provider);

        return "redirect:/providers";
    }

    //删除供应商
    @DeleteMapping("/provider/{pid}")
    public String delete(@PathVariable("pid") Integer pid) {
        logger.info("删除供应商，pid = " + pid);

        providerMapper.deleteProviderByPid(pid);

        return "redirect:/providers";
    }
}
