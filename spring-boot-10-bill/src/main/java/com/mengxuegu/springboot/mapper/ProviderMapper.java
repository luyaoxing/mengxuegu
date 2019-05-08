package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.entities.Provider;

import java.util.List;

/**
 * @author: Lunatic Princess
 * @create: 2019-02-22
 * Detailed time at 02:15
 **/

public interface ProviderMapper {

    List<Provider> getProvider(Provider provider);

    Provider getProviderByPid(Integer pid);

    int addProvider(Provider provider);

    int deleteProviderByPid(Integer pid);

    int updateProvider(Provider provider);

}
