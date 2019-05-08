package com.finalproject.springboot.mapper;

import com.finalproject.springboot.entity.Provider;

import java.util.List;

/**
 * @program: mengxuegu
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-04-29 09:58
 **/
public interface ProviderMapper {

    List<Provider> getProvider(Provider provider);

    Provider getProviderByPid(Integer pid);

    int addProvider(Provider provider);

    int deleteProviderByPid(Integer pid);

    int updateProvider(Provider provider);
}
