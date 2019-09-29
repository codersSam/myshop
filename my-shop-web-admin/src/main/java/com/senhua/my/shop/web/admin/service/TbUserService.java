package com.senhua.my.shop.web.admin.service;

import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.commons.dto.PageInfo;
import com.senhua.my.shop.commons.persistence.BaseService;
import com.senhua.my.shop.domain.TbUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/17.
 */
public interface TbUserService extends BaseService<TbUser>{
    TbUser login(String email,String password);
}
