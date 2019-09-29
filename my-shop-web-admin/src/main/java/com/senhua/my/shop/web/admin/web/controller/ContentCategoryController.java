package com.senhua.my.shop.web.admin.web.controller;
import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.domain.TbContentCategory;
import com.senhua.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.senhua.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/24.
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory,TbContentCategoryService> {

    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id){
        TbContentCategory tbContentCategory = new TbContentCategory();
        if(id != null){
            tbContentCategory = service.getById(id);
        }
        else{
        }
        return tbContentCategory;
    }
    /**
     * 跳转类别表单页
     *
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbContentCategory contentCategory) {
        return "content_category_form";
    }

    /**
     * 显示类别信息
     * @param model
     * @return
     */
    @Override
    @RequestMapping(value = "list")
    public String List(Model model){
        List<TbContentCategory> targetList = new ArrayList<TbContentCategory>();
        List<TbContentCategory> sourceList = service.selectAll();
        sortContentCategoryList(sourceList,targetList,0L);
        model.addAttribute("tbContentCategoryList",targetList);
        return "content_category_list";
    }

    /**
     * 根据id获取类别信息
     * @param id
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data", method= RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id){
        if(id == null){
            id = 0L;
        }
        return service.selectByPid(id);
    }

    /**
     * 查看分类详情
     *
     * @return
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return null;
    }

    /**
     * 保存类别信息
     *
     * @param tbContentCategory
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbContentCategory);
        //保存成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "content_category_form";
        }
    }


}
