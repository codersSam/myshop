package com.senhua.my.shop.commons.persistence;

import java.util.List;

/**
 * Created by Administrator on 2019/4/8.
 */
public interface BaseTreeDao<T extends BaseEntity> {

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
     * 根据id获取分类类别信息
     * @param parentId
     * @return
     */
    List<T> selectByPid(Long parentId);
}
