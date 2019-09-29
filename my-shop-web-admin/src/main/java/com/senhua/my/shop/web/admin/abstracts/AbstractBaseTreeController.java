package com.senhua.my.shop.web.admin.abstracts;

import com.senhua.my.shop.commons.persistence.BaseTreeEntity;
import com.senhua.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * Created by Administrator on 2019/4/11.
 */
public abstract class AbstractBaseTreeController<T extends BaseTreeEntity,S extends BaseTreeService<T>> {

    @Autowired
    protected S service;

    /**
     * 跳转表单页
     * @param entity
     * @return
     */
    public abstract String form(T entity);

    /**
     * 显示类表信息
     * @param model
     * @return
     */
    public abstract String List(Model model);

    /**
     * 查看详情
     * @return
     */
    public abstract String detail();

    /**
     * 保存信息
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 根据id获取类别信息
     * @param id
     * @return
     */
    public abstract List<T> treeData(Long id);

    /**
     * 排序
     * @param sourceList
     * @param targetList
     * @param parentId
     */
    protected void sortContentCategoryList(List<T> sourceList,List<T> targetList,Long parentId){
        for (T sourceEntity : sourceList) {
            if(sourceEntity.getParent().getId().equals(parentId)){
                targetList.add(sourceEntity);
                if(sourceEntity.getIsParent()){
                    for( T currentEntity:sourceList){
                        if (currentEntity.getParent().getId().equals(sourceEntity.getId())){
                            sortContentCategoryList(sourceList,targetList,sourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }

}
