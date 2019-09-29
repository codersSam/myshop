package com.senhua.my.shop.commons.persistence;

import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 */
public interface BaseService<T extends BaseEntity>{
    List<T> selectAll();
    T getById(Long id);
    int count(T entity);
    BaseResult save(T entity);
    void delete(Long id);
    void deleteMulti(String[] ids);
    PageInfo<T> page(int start, int length, int draw, T entity);
}
