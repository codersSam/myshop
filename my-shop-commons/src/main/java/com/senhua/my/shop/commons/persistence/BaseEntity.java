package com.senhua.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2019/1/31.
 */
@Data
public class BaseEntity implements Serializable{
    private Long id;
    private Date created;
    private Date updated;

}
