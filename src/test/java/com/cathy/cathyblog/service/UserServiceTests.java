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

        User admin1=userService.getAdmin();
        Assert.assertEquals(1,admin1.getId().intValue());
        Assert.assertEquals(admin.getUserName(),admin1.getUserName());
    }
}
