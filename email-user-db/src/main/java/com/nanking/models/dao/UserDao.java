package com.nanking.models.dao;

import com.nanking.models.domain.User;
import com.nanking.models.dto.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    public List<User> getUser(@Param("username")String username,@Param("password")String password);

    public int insertUser(User user);
}
