package com.cathy.cathyblog.service;

import com.cathy.cathyblog.CathyblogApplication;
import com.cathy.cathyblog.common.exceptions.RepositoryException;
import com.cathy.cathyblog.domain.Option;
import com.cathy.cathyblog.domain.extend.OptionKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CathyblogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticServiceTests {
    @Autowired
    OptionService optionService;
    @Autowired
    StatisticService statisticService;

    @Before
    public void init(){
        statisticService.initStatistic();
    }

    @Test
    public void incBlogCommentCount() throws RepositoryException {
        statisticService.incStatistic(OptionKey.ID_C_STATISTIC_BLOG_COMMENT_COUNT);
        statisticService.incStatistic(OptionKey.ID_C_STATISTIC_BLOG_COMMENT_COUNT);
        Option option=optionService.getByOptionKey(OptionKey.ID_C_STATISTIC_BLOG_COMMENT_COUNT);
        Assert.assertEquals("2",option.getOptionValue());
    }
}
