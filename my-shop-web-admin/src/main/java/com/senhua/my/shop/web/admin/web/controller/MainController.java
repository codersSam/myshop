package com.senhua.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2019/1/16.
 */
@Controller
public class MainController {
    @RequestMapping(value = "main" , method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}
