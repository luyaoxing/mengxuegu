package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.entities.Bill;
import com.mengxuegu.springboot.entities.BillProvider;
import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.mapper.BillMapper;
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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: spring-boot-01-hello
 * @description: 模拟500错误
 * @author: Lunatic Princess
 * @create: 2019-01-29
 * Detailed time at 21:23
 **/

@Controller
public class BillController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProviderMapper providerMapper;

    @Autowired
    BillMapper billMapper;

    @GetMapping("/bills")
    public String list(Map<String, Object> map, Bill bill) {
        logger.info("账单列表查询。。。" + bill);

        List<BillProvider> billProviders = billMapper.getBills(bill);

        List<Provider> providers = providerMapper.getProvider(null);

        map.put("billProviders", billProviders);
        map.put("providers", providers);
        //用于搜索出回显数据
        map.put("billName", bill.getBillName());
        map.put("pid", bill.getPid());
        map.put("pay", bill.getPay());
//        返回路径里不要带斜杠/ ，在源码里已经写了default-prefix前缀 classpath:/templates/，已经带了/
//                                              default-suffix后缀 .html，所以也不用带  .html。


        return "bill/list";
    }

    /**
     * type = null 进入查看详情页面view.html
     * type=update则是进入update.html
     * @return
     */
    //springmvc的内容，mapping里面的花括号，在方法参数里面同@PathVariable,获取provider里面的key pid
    @GetMapping("/bill/{bid}")
    public String view(@PathVariable("bid") Integer bid,
                       @RequestParam(value = "type", defaultValue = "view") String type,
                       Map<String, Object> map) {
        logger.info("查询：" + bid + " 的供应商的详细信息");

        BillProvider billProvider = billMapper.getBillByBid(bid);

        if ( "update".equals(type)) {
            List<Provider> providers = providerMapper.getProvider(null);
            map.put("providers", providers);
        }
        map.put("billProvider", billProvider);

//        return "provider/view";
//        type=null的时候，进入view.html，type=update则是进入update.html
        return "bill/" + type;
    }

    //修改信息
    @PutMapping("/bill")
    public String update(Bill bill) {

        logger.info("更新账单信息。。。");
        //更新操作
        billMapper.updateBill(bill);
        return "redirect:/bills";
    }

    //前往添加供应商页面
    @GetMapping("/bill")
    public String toAddPage(Map<String, Object> map) {
        //查询所有供应商
//        List<Provider> providers = providerMapper.getProvider(null);
        map.put("providers", providerMapper.getProvider(null));
        return "bill/add";
    }

    //添加供应商数据
    @PostMapping("/bill")
    public String add(Bill bill){
        logger.info("添加账单数据：" + bill);
        //新增数据操作
        billMapper.addBill(bill);
        return "redirect:/bills";
    }

    //删除
    @DeleteMapping("/bill/{bid}")
    public String delete(@PathVariable("bid") Integer bid) {
        logger.info("删除供应商，bid = " + bid);
        billMapper.deleteBillByBid(bid);
        /**
         * ******************************************************************
         * 重定向这里尤其注意！！重定向是mapping请求路径！！斜杠/也不能漏！！！
         * ******************************************************************
         * */
        return "redirect:/bills";
    }
}
