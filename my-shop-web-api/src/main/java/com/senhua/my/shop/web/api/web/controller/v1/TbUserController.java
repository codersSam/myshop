package com.senhua.my.shop.web.api.web.controller.v1;

import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.domain.TbUser;
import com.senhua.my.shop.web.api.service.TbUserService;
import com.senhua.my.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/5/27.
 */
@RestController
@RequestMapping(value = "${api.path.v1}/user")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser){

        TbUser login = tbUserService.login(tbUser);
        TbUserDTO tbUserDTO = new TbUserDTO();
        if(login == null){
            return BaseResult.fail("用户或密码错误");
        }else{
            BeanUtils.copyProperties(login,tbUserDTO);
            return BaseResult.success("成功",tbUserDTO);
        }

    }

}
