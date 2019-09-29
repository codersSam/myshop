package com.senhua.my.shop.web.admin.abstracts;

import com.senhua.my.shop.commons.persistence.BaseEntity;
import com.senhua.my.shop.commons.persistence.BaseTreeDao;
import com.senhua.my.shop.commons.persistence.BaseTreeEntity;
import com.senhua.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2019/4/9.
 */
@Transactional(readOnly = true)
public abstract class AbstractBaseTreeServiceImpl<T extends BaseTreeEntity,D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired
    private D dao;

    /**
     * 查询全部信息
     * @return
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id){
        dao.delete(id);
    }
    /**
     * 根据id获取信息
     * @param id id信息
     * @return
     */
    @Override
    public T getById(Long id){
        return dao.getById(id);
    }
    /**
     * 更新用户信息
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public void update(T entity){
        dao.update(entity);
    }
    /**
     * 根据id获取分类类别信息
     * @param parentId
     * @return
     */
    @Override
    public List<T> selectByPid(Long parentId){
        return dao.selectByPid(parentId);
    }

    /**
     * 插入
     * @param entity
     */
    @Transactional(readOnly = false)
    public void insert(T entity){
        dao.insert(entity);
    }
}
