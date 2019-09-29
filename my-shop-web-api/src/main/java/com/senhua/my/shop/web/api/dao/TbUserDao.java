package com.senhua.my.shop.web.api.dao;

import com.senhua.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2019/5/27.
 */
@Repository
public interface TbUserDao {

    TbUser login(TbUser tbUser);
}
