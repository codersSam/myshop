package com.senhua.my.shop.web.admin.abstracts;

import com.senhua.my.shop.commons.dto.PageInfo;
import com.senhua.my.shop.commons.persistence.BaseDao;
import com.senhua.my.shop.commons.persistence.BaseEntity;
import com.senhua.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/9.
 */
@Transactional(readOnly = true)
public abstract class AbstractBaseServiceImpl<T extends BaseEntity,D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] ids) {
        dao.deleteMulti(ids);
    }

    @Override
    public int count(T entity) {
        return dao.count(entity);
    }

    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        PageInfo<T> pageInfo = new PageInfo<T>();
        int count = count(entity);
        Map<String, Object> parama = new HashMap<String, Object>();
        parama.put("start", start);
        parama.put("length", length);
        parama.put("PageParam", entity);

        pageInfo.setDraw(draw);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setData(dao.page(parama));

        return pageInfo;
    }
}
