package com.cathy.cathyblog.service;

import com.cathy.cathyblog.CathyblogApplication;
import com.cathy.cathyblog.common.exceptions.ServiceException;
import com.cathy.cathyblog.domain.Option;
import com.cathy.cathyblog.domain.extend.OptionKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CathyblogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OptionServiceTests {
    @Autowired
    OptionService optionService;
    @Test
    public void test() throws ServiceException {
        Option option=new Option();
        option.setOptionKey("test");
        option.setOptionValue("testvalue");
        option.setOptionCategory(OptionKey.CATEGORY_C_PREFERENCE);
        optionService.add(option);
    }
}
