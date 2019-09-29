package com.senhua.my.shop.domain;

import com.senhua.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2019/3/2.
 */
@Data
public class TbContent extends BaseEntity{

    @Length(min = 2, max = 10, message = "标题长度必须介于 2 和 10 之间")
    private String title;
    @Length(min = 2, max = 10, message = "子标题长度必须介于 6 和 10 之间")
    private String subTitle;
    @Length(min = 6, max = 50, message = "标题描述必须介于 6 和 50 之间")
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;

    @NotNull(message = "父级类目不能为空")
    private TbContentCategory tbContentCategory;

}
