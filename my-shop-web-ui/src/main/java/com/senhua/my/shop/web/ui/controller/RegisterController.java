package com.senhua.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2019/5/27.
 */
@Controller
public class RegisterController {

    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }
}
