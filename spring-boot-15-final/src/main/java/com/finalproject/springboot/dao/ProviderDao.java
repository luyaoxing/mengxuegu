package com.finalproject.springboot.dao;

import com.finalproject.springboot.entity.Provider;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @program: mengxuegu
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-04-10 16:58
 **/
//这个注解把下面的dao纳入到容器当中
@Repository
public class ProviderDao {

    private static Map<Integer, Provider> providerMap = null;
    private static Integer initId = 2006;

    static {
        providerMap = new HashMap<Integer, Provider>();

        providerMap.put(2001, new Provider(2001, "PR-AA", "SONY", "张先生", "18888666981", "日本国东京都港区港南1-7-1", "0911-0123456", "品质A", "Sony.jpg"));
        providerMap.put(2002, new Provider(2002, "PR-BB", "Panasonic", "李先生", "18888666982", "日本国东京都港区港南1-7-1", "0911-0123453", "品质B", "Sony.jpg"));
        providerMap.put(2003, new Provider(2003, "PR-CC", "Nikon", "刘先生", "18888666983", "日本国东京都港区港南1-7-1", "0911-0123454", "品质C", "PENTAX.jpg"));
        providerMap.put(2004, new Provider(2004, "PR-DD", "Canon", "萧先生", "18888666984", "日本国东京都港区港南1-7-1", "0911-0123451", "品质D", "OLYMPUS.jpg"));
        providerMap.put(2005, new Provider(2005, "PR-EE", "LEICA", "陈先生", "18888666985", "日本国东京都港区港南1-7-1", "0911-0123452", "品质E", "Nikon.jpg"));
        providerMap.put(2006, new Provider(2005, "PR-EE", "LEICA", "陈先生", "18888666985", "日本国东京都港区港南1-7-1", "0911-0123452", "品质E", "LEICA.jpg"));

    }


    //如果参数为空，那么表示它是一个新增操作。如果不为空，那么就是更新操作
    public void save(Provider provider){
        if(provider.getPid() == null){
            provider.setPid(initId++);
        }
        provider.setCreateDate(new Date());
        providerMap.put(provider.getPid(), provider);
    }

    //一个有参数一个无参数，通过这个方法重载。
    public Collection<Provider> getAll(){
        return providerMap.values();
    }

    public Collection<Provider> getAll(String providerName){
        Collection<Provider> providers = getAll();

        //不为空
        if( !StringUtils.isEmpty( providerName )) {
            int count = 0;
            for (Provider provider: providers) {
                //包含则存在
                /**contains方法：检查串中是否包含着指定子串*/
                if ( provider.getProviderName().toUpperCase().contains(  providerName.toUpperCase() ) ) {
                    count++;
                    //count>1 表示集合至少有一个存在的用户, 否则创建新的集合
                    providers = count > 1 ? providers : new ArrayList<Provider>();
                    providers.add(provider);
                }//没有查询到一条数据
                if (count == 0) {
                    providers = new ArrayList<Provider>();
                }
            }
        }
        return providers;
    }

    public Provider getProvider(Integer pid){
        return providerMap.get(pid);
    }

    public void delete(Integer pid){
        providerMap.remove(pid);
    }
}

