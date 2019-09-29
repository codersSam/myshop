package com.senhua.my.shop.web.admin.service.impl;

import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.commons.validator.BeanValidator;
import com.senhua.my.shop.domain.TbContentCategory;
import com.senhua.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.senhua.my.shop.web.admin.dao.TbContentCategoryDao;
import com.senhua.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2019/2/24.
 */
@Service
@Transactional(readOnly = true)
public class TbContentCateogryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService{

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        entity.setUpdated(new Date());
        String message = BeanValidator.validator(entity);
        if(message == null){
            //父节点对象形参数
            TbContentCategory parent = entity.getParent();
            //新增用户
            if (entity.getId() == null) {
                entity.setCreated(new Date());
                //如果当前节点没有父节点，则将父节点设置为根结点
                if(parent == null || parent.getId() == null || parent.getId() == 0){
                    parent.setId(0L);
                }

                if(parent.getId()!=0){
                    //当前父节点的对象
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    currentCategoryParent.setIsParent(true);
                    update(currentCategoryParent);
                }

                entity.setIsParent(false);
                insert(entity);
            }
            //编辑用户
            else {
                update(entity);
            }
            return BaseResult.success("保存用户信息成功");
        }
        else{
            return BaseResult.fail(message);
        }
    }
}
