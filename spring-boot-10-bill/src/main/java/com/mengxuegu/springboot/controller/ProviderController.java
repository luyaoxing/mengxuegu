package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.dao.ProviderDao;
import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @program: spring-boot-01-hello
 * @description: bill manage system, provider de controller
 * @author: Lunatic Princess
 * @create: 2019-01-26
 * Detailed time at 14:03
 **/
@Controller
public class ProviderController {
    Logger logger = LoggerFactory.getLogger(getClass());

    //    @RequestMapping(value = "list",method = RequestMethod.GET);
//    SpringMvc的新版本特性，已经可以使用下面的更简洁的方法了。
    @Autowired
    ProviderDao providerDao;

    @Autowired
    ProviderMapper providerMapper;

    @GetMapping("/providers")
    public String list(Map<String, Object> map, Provider provider) {
        logger.info("供应商列表查询。。。" + provider);

        List<Provider> providers = providerMapper.getProvider(provider);

        map.put("providers", providers);
        map.put("providerName", provider.getProviderName());
//        返回路径里不要带斜杠/ ，在源码里已经写了default-prefix前缀 classpath:/templates/，已经带了/
//                                              default-suffix后缀 .html，所以也不用带  .html。


        return "provider/list";
    }

    /**
     * type = null 进入查看详情页面view.html
     * type=update则是进入update.html
     *
     * @param pid  供应商id
     * @param type
     * @param map
     * @return
     */


    //springmvc的内容，mapping里面的花括号，在方法参数里面同@PathVariable,获取provider里面的key pid
    @GetMapping("/provider/{pid}")
    public String view(@PathVariable("pid") Integer pid,
                       @RequestParam(value = "type", defaultValue = "view") String type,
                       Map<String, Object> map) {
        logger.info("查询：" + pid + " 的供应商的详细信息");

        Provider provider = providerMapper.getProviderByPid(pid);

        map.put("provider", provider);

//        return "provider/view";
//        type=null的时候，进入view.html，type=update则是进入update.html
        return "provider/" + type;
    }

    //修改供应商信息
    @PutMapping("/provider")
    public String update(Provider provider) {

        logger.info("更新供应商信息。。。");
        //更新操作
        providerMapper.updateProvider(provider);
        return "redirect:/providers";
    }

    //前往添加供应商页面
    @GetMapping("/provider")
    public String toAddPage() {
        return "provider/add";
    }

    //添加供应商数据
    @PostMapping("/provider")
    public String add(Provider provider){
        logger.info("添加供应商数据：" + provider);
        //新增数据操作
        providerMapper.addProvider(provider);
        return "redirect:/providers";
    }

    //删除供应商
    @DeleteMapping("/provider/{pid}")
    public String delete(@PathVariable("pid") Integer pid) {
        logger.info("删除供应商，pid = " + pid);
        providerMapper.deleteProviderByPid(pid);
        /**
         * ******************************************************************
         * 重定向这里尤其注意！！重定向是mapping请求路径！！斜杠/也不能漏！！！
         * ******************************************************************
         * */
        return "redirect:/providers";
    }
}
