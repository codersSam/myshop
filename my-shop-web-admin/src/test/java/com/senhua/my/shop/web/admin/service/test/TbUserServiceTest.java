package com.senhua.my.shop.web.admin.service.test;

import com.senhua.my.shop.domain.TbUser;
import com.senhua.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/1/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml",
"classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUserList = tbUserService.selectAll();
        for(TbUser tbUser : tbUserList){
            System.out.println(tbUser.getUsername());
        }
    }
    @Test
    public void testGetById(){
        TbUser tbUser = tbUserService.getById(36L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void testInsert(){
        TbUser tbUser = new TbUser();
        tbUser.setUsername("senhua");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("11111111".getBytes()));
        tbUser.setEmail("senhua@senhua.com");
        tbUser.setPhone("11391232144");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete(){
        tbUserService.delete(38L);
    }
}
