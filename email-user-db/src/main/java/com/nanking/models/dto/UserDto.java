package com.nanking.models.dto;

import com.nanking.models.domain.User;

import javax.validation.Valid;
import java.util.List;

public class UserDto {

    @Valid
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
