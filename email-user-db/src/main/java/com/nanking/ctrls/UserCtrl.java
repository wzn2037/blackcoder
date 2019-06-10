package com.nanking.ctrls;

import com.nanking.common.Result;
import com.nanking.models.domain.User;
import com.nanking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserCtrl extends BaseCtrl {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public Result getUsers(User user){

        return send(userService.getUsers(user));

    }
    @GetMapping(value = "/insertUsers")
    public Result insertUsers(User user){
        return send(userService.insertUsers(user));
    }
}
