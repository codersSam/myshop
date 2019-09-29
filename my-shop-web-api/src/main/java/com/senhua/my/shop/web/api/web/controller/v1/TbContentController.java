package com.senhua.my.shop.web.api.web.controller.v1;

import com.senhua.my.shop.commons.dto.BaseResult;
import com.senhua.my.shop.domain.TbContent;
import com.senhua.my.shop.domain.TbContentCategory;
import com.senhua.my.shop.web.api.service.TbContentService;
import com.senhua.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */
@RestController
@RequestMapping(value = "${api.path.v1}/content")
public class TbContentController {
    @Autowired
    private TbContentService tbContentService;

    public final Long PPT_CATAGORY_ID = 89L;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;
        if(id == null){
            tbContent = new TbContent();
        }
        return tbContent;
    }
    @RequestMapping(value = "ppt",method = RequestMethod.GET)
    public BaseResult findContentByCategoryId(){
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(PPT_CATAGORY_ID);

        if(tbContents != null && tbContents.size()>0){
            tbContentDTOS = new ArrayList<>();
            for(TbContent tbContent:tbContents){
                TbContentDTO tbContentDTO = new TbContentDTO();
                BeanUtils.copyProperties(tbContent,tbContentDTO);
                tbContentDTOS.add(tbContentDTO);
            }
        }

        return BaseResult.success("成功",tbContentDTOS);
    }

}
