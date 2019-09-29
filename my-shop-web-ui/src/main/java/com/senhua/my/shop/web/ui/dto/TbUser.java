package com.senhua.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/5/28.
 */
@Data
public class TbUser implements Serializable {
    private Long id;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String verification;
}
