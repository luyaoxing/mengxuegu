package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.entities.User;

import java.util.List;

/**
 * @author: Lunatic Princess
 * @create: 2019-02-22
 * Detailed time at 02:15
 **/

public interface UserMapper {

    User getUserByUsername(String username);

    List<User> getUsers(User user);

    User getUserById(Integer id);

    int addUser(User User);

    int deleteUserById(Integer id);

    int updateUser(User user);

}
