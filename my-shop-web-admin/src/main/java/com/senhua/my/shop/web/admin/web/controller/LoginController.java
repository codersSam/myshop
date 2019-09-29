package com.senhua.my.shop.web.admin.web.controller;

import com.senhua.my.shop.domain.TbUser;
import com.senhua.my.shop.web.admin.service.TbUserService;
import com.senhua.my.shop.commons.Utils.CookieUtils;
import com.senhua.my.shop.commons.constant.ConstantUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019/1/16.
 */
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 登录跳转
     * @return
     */
    @RequestMapping(value = {"","login"} , method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest){
        String userInfo = CookieUtils.getCookieValue(httpServletRequest, ConstantUtils.COOKIE_NAME_USER_INFO);
        if(!StringUtils.isBlank(userInfo)){
            String[] userInfoArray = userInfo.split(":");
            String email = userInfoArray[0];
            String password = userInfoArray[1];
            httpServletRequest.setAttribute("email",email);
            httpServletRequest.setAttribute("password",password);
            httpServletRequest.setAttribute("isRemember",true);
        }
        return "login";
    }

    /**
     * 登录逻辑
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password,
                        HttpServletRequest httpServletRequest, Model model, HttpServletResponse httpServletResponse){

        TbUser tbUser = tbUserService.login(email, password);

        //是否记住我
        boolean isRemember = httpServletRequest.getParameter("isRemember") == null? false:true;
        if(!isRemember){
            CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,ConstantUtils.COOKIE_NAME_USER_INFO);
        }
        //登录失败
        if(tbUser == null){
            model.addAttribute("message","邮箱或者密码不正确");
            return login(httpServletRequest);
        }
        //登录成功
        else{
            //设置Cookie信息
            if(isRemember == true){
            CookieUtils.setCookie(httpServletRequest,httpServletResponse,ConstantUtils.COOKIE_NAME_USER_INFO,String
                    .format("%s:%s",email,password),7*24*60*60);
        }
            //将登录信息写进会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String lagout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return login(httpServletRequest);
    }
}
