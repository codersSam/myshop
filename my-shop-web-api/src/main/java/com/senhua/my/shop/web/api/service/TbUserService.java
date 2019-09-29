package com.senhua.my.shop.web.api.service;

import com.senhua.my.shop.domain.TbUser;

/**
 * Created by Administrator on 2019/5/27.
 */
public interface TbUserService {

    /**
     * 会员登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);

}

