package com.senhua.my.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 会员数据传输对象
 * Created by Administrator on 2019/5/28.
 */
@Data
public class TbUserDTO implements Serializable {

    private Long id;
    private String username;
    private String phone;
    private String email;
    @JsonIgnore
    private String password;

}


