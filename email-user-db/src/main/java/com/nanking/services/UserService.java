package com.nanking.services;

import com.nanking.common.StringUtil;
import com.nanking.models.dao.UserDao;
import com.nanking.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    public UserDao userdao;

    public Map<String,Object> getUsers(User u){
        List<User> userList = userdao.getUser(u.getUsername(),StringUtil.encrypt(u.getPassword()));
        if(userList!=null){
            for( int i=0;i<userList.size();i++){
                User user = userList.get(i);
                user.setPassword(StringUtil.decrypt(user.getPassword()));
            }

        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("all",userList.size());
        map.put("data",userList);
        return map;
    }

    public int insertUsers(User user){
        user.setPassword(StringUtil.encrypt(user.getPassword()));
        int data = userdao.insertUser(user);
        return data;
    }
}
