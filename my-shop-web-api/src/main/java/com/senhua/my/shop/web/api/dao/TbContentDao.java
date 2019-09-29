package com.senhua.my.shop.web.api.dao;

import com.senhua.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */
@Repository
public interface TbContentDao {
    /**
     * 根据类别ID查询内容列表
     * @param tbContent
     * @return
     */
    List<TbContent> selectByCategoryId(TbContent tbContent);
}
