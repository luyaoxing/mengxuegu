package com.finalproject.springboot.controller;

import com.finalproject.springboot.dto.Exposer;
import com.finalproject.springboot.dto.SeckillExecution;
import com.finalproject.springboot.dto.SeckillkResult;
import com.finalproject.springboot.entity.Seckill;
import com.finalproject.springboot.enums.SeckillStatEnum;
import com.finalproject.springboot.exception.RepeatKillException;
import com.finalproject.springboot.exception.SeckillCloseException;
import com.finalproject.springboot.exception.SeckillException;
import com.finalproject.springboot.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: mengxuegu
 * @description: 抢购控制
 * @author: Lunatic Princess
 * @create: 2019-05-05 21:13
 **/
@Controller
public class PurchaseController {

    @Autowired
    private SeckillService seckillService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/sells")
    public String findSeckillList(Model model) {
        List<Seckill> list = seckillService.findAll();
        model.addAttribute("list", list);
        return "sell/purchase";
    }

    @ResponseBody
    @GetMapping("/sell/{id1}")
    public Seckill findById(@RequestParam("id") Long id) {
        return seckillService.findById(id);
    }

    //    @RequestMapping("/{seckillId}/purchaseView")
    @GetMapping("/purchaseView/{seckillId}")
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "sell/purchase";
        }
        Seckill seckill = seckillService.findById(seckillId);
        logger.info(String.valueOf(seckill));
        model.addAttribute("seckill", seckill);
        if (seckill == null) {
            return "sell/purchase";
        }
        return "sell/purchase_view";
    }

    @ResponseBody
    @PostMapping(value = "/{seckillId}/exposer", produces = {"application/json;charset=UTF-8"})
    public SeckillkResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillkResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillkResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillkResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @PostMapping(value = "/{seckillId}/{md5}/execution", produces = {"application/json;charset=UTF-8"})
    public SeckillkResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @RequestParam("money") BigDecimal money,
                                                   @CookieValue(value = "killPhone", required = false) Long userPhone) {
        if (userPhone == null) {
            return new SeckillkResult<SeckillExecution>(false, "未注册");
        }

        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, money, userPhone, md5);
            return new SeckillkResult<SeckillExecution>(true, execution);
        } catch (RepeatKillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillkResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillCloseException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillkResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillkResult<SeckillExecution>(true, seckillExecution);
        }
    }

    @GetMapping("/toPurchasePage")
    public String toPurchasePage() {
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping(value = "/time/now")
    public SeckillkResult<Long> time() {
        Date now = new Date();
        return new SeckillkResult(true, now.getTime());
    }
}
