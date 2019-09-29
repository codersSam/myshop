package com.senhua.my.shop.web.admin.web.controller;
import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.commons.dto.PageInfo;
import com.senhua.my.shop.commons.validator.BeanValidator;
import com.senhua.my.shop.domain.TbContent;
import com.senhua.my.shop.domain.TbUser;
import com.senhua.my.shop.web.admin.abstracts.AbstractBaseController;
import com.senhua.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/3/2.
 */
@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<TbContent,TbContentService>{


    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = new TbContent();
        if(id != null){
            tbContent = service.getById(id);
        }
        else{
        }
        return tbContent;
    }
    /**
     * 跳转用户列表页
     *
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        /*List<TbUser> tbUserList = tbUserService.selectAll();
        model.addAttribute("tbUserList",tbUserList);*/
        return "content_list";

    }

    /**
     * 跳转用户表单页
     *
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }

    /**
     * 查看用户详情
     *
     * @return
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "content_detail";
    }

    /**
     * 保存用户信息
     *
     * @param tbContent
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes) {
        String message = BeanValidator.validator(tbContent);
        //保存成功
        if (message == null) {
            BaseResult baseResult = service.save(tbContent);;
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        }
        //保存失败
        else {
            BaseResult baseResult = BaseResult.fail(message);
            model.addAttribute("baseResult", baseResult);
            return "content_form";
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String idArray[] = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除成功");
        } else {
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

}
