package com.nanking.models.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nanking.common.StringUtil;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

        //private static final long serialVersionUID = 4125096758372084309L;
        private static final long serialVersionUID = -8366929034564774130L;
        private Integer id;
        private String username;


    private String password;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
