package com.senhua.my.shop.web.admin.dao;

import com.senhua.my.shop.commons.persistence.BaseDao;
import com.senhua.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/17.
 */
@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    /**
     * 根据邮箱获得用户信息
     * @param email
     * @return
     */
    TbUser getByEmail(String email);
}
