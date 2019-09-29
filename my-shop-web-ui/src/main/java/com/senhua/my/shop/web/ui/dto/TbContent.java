package com.senhua.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/5/26.
 */
@Data
public class TbContent implements Serializable {

    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}

