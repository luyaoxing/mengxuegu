package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot-10-bill
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-03-17
 * Detailed time at 15:38
 **/

@RestController
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @GetMapping("/provider/{pid}")
    public Provider getProvider(@PathVariable("pid") Integer pid) {
        return providerService.getProviderByPid(pid);
    }

    @GetMapping("/updateProvider")
    public Integer updateProvider(Provider provider) {
        return providerService.updateProvider(provider);
    }

    @GetMapping("/addProvider")
    public Integer addProvider(Provider provider) {
        return providerService.addProvider(provider);
    }

    @GetMapping("/deleteProviderByPid/{pid}")
    public Integer deleteProviderByPid(@PathVariable("pid") Integer pid) {
        providerService.deleteProviderByPid(pid);
        return pid;
    }
}
