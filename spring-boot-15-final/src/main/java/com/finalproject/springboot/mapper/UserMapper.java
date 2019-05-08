package com.finalproject.springboot.mapper;

import com.finalproject.springboot.entity.Provider;
import com.finalproject.springboot.entity.User;

import java.util.List;

/**
 * @program: mengxuegu
 * @description:
 * @author: Lunatic Princess
 * @create: 2019-04-29 09:58
 **/
public interface UserMapper {

    List<Provider> getUser(User user);

    User getUserById(Integer id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);
}
