package com.cathy.cathyblog.service;

import com.cathy.cathyblog.CathyblogApplication;
import com.cathy.cathyblog.common.consts.UserRoles;
import com.cathy.cathyblog.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CathyblogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTests {
    @Autowired
    UserService userService;
    @Test
    public void test(){
        User admin=new User();
        admin.setUserRole(UserRoles.ADMIN_ROLE);
        admin.setUserName("admin");
        admin.setUserPassword("pwd");
        userService.save(admin);

        admin=userService.getAdmin();
        Assert.assertEquals(1,admin.getId().intValue());
        Assert.assertEquals("admin",admin.getUserName());

        admin.setUserArticleCount(1);
        admin.setUserPublishedArticleCount(1);
        userService.save(admin);
        admin=userService.getAdmin();
        Assert.assertEquals(1,admin.getUserArticleCount().intValue());
        Assert.assertEquals(1,admin.getUserPublishedArticleCount().intValue());
    }
}
