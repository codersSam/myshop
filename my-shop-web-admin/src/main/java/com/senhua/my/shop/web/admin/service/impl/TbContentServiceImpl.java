package com.senhua.my.shop.web.admin.service.impl;

import com.senhua.my.shop.commons.Utils.RegexpUtils;
import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.commons.dto.PageInfo;
import com.senhua.my.shop.domain.TbContent;

import com.senhua.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.senhua.my.shop.web.admin.dao.TbContentDao;

import com.senhua.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/2.
 */
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent,TbContentDao> implements TbContentService {


    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        //新增用户
        if (tbContent.getId() == null) {
            tbContent.setCreated(new Date());
            dao.insert(tbContent);
        }
        //编辑用户
        else {
            dao.update(tbContent);
        }
        return BaseResult.success("保存用户信息成功");
    }

}
