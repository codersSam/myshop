package com.senhua.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/20.
 */
public interface BaseDao <T>{
    /**
     * 查询全部信息
     * @return
     */
    List<T> selectAll();

    /**
     * 根据id获取信息
     * @param id id信息
     * @return
     */
    T getById(Long id);


    /**
     * 分页查询
     * @param pamram
     * @return
     */
    List<T> page(Map<String,Object> pamram);

    /**
     * 查询总页数
     *
     * @return
     */
    int count(T entity);

    /**
     *新增用户信息
     * @param entity
     */
    void insert(T entity);

    /**
     * 更新用户信息
     * @param entity
     */
    void update(T entity);

    /**
     * 根据用户id信息删除
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);
}
