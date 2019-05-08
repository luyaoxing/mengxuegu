package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.entities.Provider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author: Lunatic Princess
 * @create: 2019-03-17
 * Detailed time at 15:12
 **/

public interface ProviderMapper {

    @Select("select * from provider where pid = #{pid}")
    Provider getProviderByPid(Integer pid);

    @Insert("INSERT INTO `provider` (`provider_code`, `providerName`, `people`, `phone`, `address`, `fax`, `describe`, `create_date`) " +
            "VALUES (#{providerCode}, #{providerName}, #{people}, #{phone}, #{address}, #{fax}, #{describe}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "pid")
    int addProvider(Provider provider);

    @Update("UPDATE `provider` " +
            "SET `provider_code`=#{providerCode}, `providerName`=#{providerName}," +
            " `people`=#{people}, `phone`=#{phone}, `address`=#{address}, " +
            "`fax`=#{fax}, `describe`=#{describe}, `create_date`=now() " +
            "WHERE `pid`=#{pid}")
    int updateProvider(Provider provider);

    @Delete("delete from provider where pid = #{pid}")
    int deleteProviderByPid(Integer pid);
}
