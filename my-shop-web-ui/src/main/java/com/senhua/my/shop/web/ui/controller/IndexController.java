package com.senhua.my.shop.web.ui.controller;

import com.senhua.my.shop.web.ui.api.v1.ContentsApi;
import com.senhua.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */
@Controller
public class IndexController {

    /**
     *门户首页
     * @return
     */
    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public String index(Model model){
        requestContentsPPT(model);
        return "index";
    }
    /**
     * 请求幻灯片
     * @param model
     */
    private void requestContentsPPT(Model model){
        List<TbContent> tbContents = ContentsApi.ppt();
        model.addAttribute("ppt",tbContents);
    }

   @RequestMapping(value = "logout" ,method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "redirect:index";
    }

}
