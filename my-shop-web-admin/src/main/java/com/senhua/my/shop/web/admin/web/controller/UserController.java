package com.senhua.my.shop.web.admin.web.controller;

import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.domain.TbUser;
import com.senhua.my.shop.web.admin.abstracts.AbstractBaseController;
import com.senhua.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 用户管理
 * Created by Administrator on 2019/1/19.
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = new TbUser();
        if(id != null){
            tbUser = service.getById(id);
        }
        else{
        }
        return tbUser;
    }

    /**
     * 跳转用户列表页
     * @return
     */
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        /*List<TbUser> tbUserList = tbUserService.selectAll();
        model.addAttribute("tbUserList",tbUserList);*/
        return "user_list";

    }

    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value = "form" ,method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    /**
     * 保存用户信息
     * @param tbUser
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    public String save(TbUser tbUser,Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbUser);
        //保存成功
        if(baseResult.getStatus() == 200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }

    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete" ,method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;

       if(StringUtils.isNotBlank(ids)){
           String idArray[] = ids.split(",");
           service.deleteMulti(idArray);
           baseResult = BaseResult.success("删除成功");
        }else{
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }


/*  没必要，直接使用批量删除的来复用
    /**
     * 单个删除
     * @param id
     * @return
     *//*
    @Override
    @ResponseBody
    @RequestMapping(value="delete/one",method = RequestMethod.POST)
    public BaseResult deleteOne(Long id){
        BaseResult baseResult = null;
        service.delete(id);
        baseResult = BaseResult.success("删除成功");
        return baseResult;
    }*/


    /**
     * 查看用户详情
     * @return
     */
    @Override
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(){
        return "user_detail";
    }
}
