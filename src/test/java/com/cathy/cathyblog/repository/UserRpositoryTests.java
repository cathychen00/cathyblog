package com.cathy.cathyblog.repository;

import com.cathy.cathyblog.CathyblogApplication;
import com.cathy.cathyblog.common.consts.UserRoles;
import com.cathy.cathyblog.domain.User;
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
        System.out.println(admins.size());
    }
}
