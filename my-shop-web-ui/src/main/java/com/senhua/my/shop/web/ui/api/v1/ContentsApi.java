package com.senhua.my.shop.web.ui.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senhua.my.shop.commons.Utils.HttpClientUtils;
import com.senhua.my.shop.commons.Utils.MapperUtils;
import com.senhua.my.shop.web.ui.dto.TbContent;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/5/27.
 */
public class ContentsApi {
    public static List<TbContent> ppt(){
        String url = API.API_CONTENTS_PPT;
        String result = HttpClientUtils.doGet(url);
        List<TbContent> tbContents = null;
        try {
            tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
}
