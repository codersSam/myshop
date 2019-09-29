package com.senhua.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;


/**
 * Created by Administrator on 2019/4/11.
 */
@Data
public class BaseTreeEntity<T extends BaseTreeEntity> extends BaseEntity implements Serializable{
    private T parent;
    private Boolean isParent;
}
