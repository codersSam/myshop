package com.senhua.my.shop.web.admin.service.impl;

import com.senhua.my.shop.commons.Utils.RegexpUtils;
import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.commons.dto.PageInfo;
import com.senhua.my.shop.commons.validator.BeanValidator;
import com.senhua.my.shop.domain.TbUser;
import com.senhua.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.senhua.my.shop.web.admin.dao.TbUserDao;
import com.senhua.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/17.
 */
@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService {


    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser tbUser) {
        String message = BeanValidator.validator(tbUser);

        if(message == null) {
            tbUser.setUpdated(new Date());
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            //新增用户
            if (tbUser.getId() == null) {
                tbUser.setCreated(new Date());
                dao.insert(tbUser);
            }
            //编辑用户
            else {
                dao.update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }
        else{
            return BaseResult.fail(message);
        }
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail(email);
        if(tbUser!=null){
            //明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //判断加密后的密码与数据库中存放的密码是否匹配，匹配则登录
            if(md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
            return tbUser;
        }
        return null;
    }
}
