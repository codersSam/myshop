package com.senhua.my.shop.web.api.service.impl;

import com.senhua.my.shop.domain.TbUser;
import com.senhua.my.shop.web.api.dao.TbUserDao;
import com.senhua.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * Created by Administrator on 2019/5/27.
 */
@Service
public class TbUserServiceImpl implements TbUserService{

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser login = tbUserDao.login(tbUser);
        if(login != null){
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if(!password.equals(login.getPassword())){
               login = null;
            }
        }
        return login;
    }
}
