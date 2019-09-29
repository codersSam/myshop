package com.senhua.my.shop.web.ui.api.v1;

import com.senhua.my.shop.commons.Utils.HttpClientUtils;
import com.senhua.my.shop.commons.Utils.MapperUtils;
import com.senhua.my.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/28.
 */
public class UsersApi {
    public static TbUser login(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));
        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));

        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);

        return user;
    }
}
