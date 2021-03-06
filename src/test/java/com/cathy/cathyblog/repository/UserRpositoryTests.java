package com.cathy.cathyblog.repository;

import com.cathy.cathyblog.CathyblogApplication;
import com.cathy.cathyblog.common.consts.UserRoles;
import com.cathy.cathyblog.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CathyblogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserRpositoryTests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void findAllByUserRole(){
        List<User> admins=userRepository.findAllByUserRole(UserRoles.ADMIN_ROLE);
        Assert.assertEquals(0,admins.size());
    }

    @Test
    public void save(){
        User admin=new User();
        admin.setUserRole(UserRoles.ADMIN_ROLE);
        admin.setUserName("admin");
        admin.setUserPassword("pwd");
        userRepository.save(admin);
        Assert.assertEquals(1,admin.getId().intValue());
    }
}
