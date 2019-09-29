package com.senhua.my.shop.domain;

import com.senhua.my.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2019/2/24.
 */
@Data
public class TbContentCategory extends BaseTreeEntity<TbContentCategory> {

    @Length(min = 1,max = 20,message = "类别名称必须在1-20个字符之间")
    private String 	name;
    private Integer status;

    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

    private TbContentCategory parent;

}
