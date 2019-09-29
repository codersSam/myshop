package com.senhua.my.shop.web.admin.abstracts;

import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.commons.dto.PageInfo;
import com.senhua.my.shop.commons.persistence.BaseEntity;
import com.senhua.my.shop.commons.persistence.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/4/10.
 */
public abstract class AbstractBaseController<T extends BaseEntity,S extends BaseService<T>> {

    @Autowired
    protected S service;

    /**
     * 跳转列表页
     * @return
     */
    public abstract String list();

    /**
     * 跳转表单页
     * @return
     */
    public abstract String form();

    /**
     * 跳转详情
     * @return
     */
    public abstract String detail();

    /**
     * 保存信息
     * @param entityy
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entityy, Model model, RedirectAttributes redirectAttributes);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);

/*  没必要，可以直接在批量删除了复用
    /**
     * 删除一个
     * @param id
     * @return
     *//*
    public abstract BaseResult deleteOne(Long id);*/

    /**
     * 分页
     * @param httpServletRequest
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<T> page(HttpServletRequest httpServletRequest, T entity){
        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLenght = httpServletRequest.getParameter("length");

        int draw = StringUtils.isBlank(strDraw) ? 0 : Integer.parseInt(strDraw);
        int start = StringUtils.isBlank(strStart) ? 0 : Integer.parseInt(strStart);
        int length = StringUtils.isBlank(strLenght) ? 10 : Integer.parseInt(strLenght);

        PageInfo<T> pageInfo = service.page(start,length,draw,entity);
        return pageInfo;
    }

}
