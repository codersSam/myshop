package com.senhua.my.shop.web.api.service;

import com.senhua.my.shop.domain.TbContent;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */
public interface TbContentService {
    /**
     * 根据类别ID查询内容列表
     * @param categoryId
     * @return
     */
    List<TbContent> selectByCategoryId(Long categoryId);
}
