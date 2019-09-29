package com.senhua.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senhua.my.shop.commons.Utils.RegexpUtils;
import com.senhua.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2019/1/17.
 */
@Data
public class TbUser extends BaseEntity {

    @Length(min = 6, max = 20, message = "用户名长度必须介于 6 和 20 之间")
    private String username;
    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码长度必须介于 6 和 20 之间")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE ,message = "手机格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL ,message = "邮箱格式不正确")
    private String email;


}
