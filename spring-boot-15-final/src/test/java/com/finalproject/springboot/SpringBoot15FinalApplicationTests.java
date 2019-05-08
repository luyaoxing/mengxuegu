package com.finalproject.springboot;

import com.finalproject.springboot.entity.Provider;
import com.finalproject.springboot.mapper.ProviderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot15FinalApplicationTests {

    @Autowired
    ProviderMapper providerMapper;

    /** getProvider单元测试 */
    @Test
    public void contextLoads() {
        Provider provider = new Provider();
        provider.setProviderName("A货");
        List<Provider> providers = providerMapper.getProvider(provider);
        System.out.println(providers.get(0));
    }

    @Test
    /** getProviderByPid单元测试 */
    public void getProviderByPid(){
        Provider provider = new Provider();
        provider = providerMapper.getProviderByPid(2);
        System.out.println(provider);
    }
}
