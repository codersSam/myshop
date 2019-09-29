package com.senhua.my.shop.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.senhua.my.shop.commons.Utils.EmailSendUtils;
import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.web.ui.api.v1.UsersApi;
import com.senhua.my.shop.web.ui.constant.SystemConstant;
import com.senhua.my.shop.web.ui.dto.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/5/27.
 */
@Controller
public class LoginController {

    @Autowired
    private EmailSendUtils emailSendUtils;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest httpServletRequest) throws Exception {

        //验证失败
        if(!checkVerification(tbUser,httpServletRequest)){
            model.addAttribute("baseResult",BaseResult.fail("验证码验证失败，请重新输入"));
            return "login";
        }

        TbUser user = UsersApi.login(tbUser);
        if(user == null){
            model.addAttribute("baseResult", BaseResult.fail("用户名或密码错误，请重新输入！！"));
            return "login";
        }else{
            emailSendUtils.send("登录信息",String.format("用户【%s】登录MyShop",tbUser.getUsername()),"1139140536@qq.com");
            httpServletRequest.getSession().setAttribute(SystemConstant.SESSION_USER_KEY,user);
            return "redirect:/index";
        }
    }

    private boolean checkVerification(TbUser tbUser,HttpServletRequest httpServletRequest){
        String verification = (String) httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(StringUtils.equals(verification,tbUser.getVerification())){
            return true;
        }else{
            return false;
        }
    }

}
