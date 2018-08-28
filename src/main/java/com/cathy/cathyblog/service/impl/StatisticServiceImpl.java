package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.common.exceptions.RepositoryException;
import com.cathy.cathyblog.domain.Option;
import com.cathy.cathyblog.domain.extend.OptionKey;
import com.cathy.cathyblog.repository.OptionRepository;
import com.cathy.cathyblog.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cathy.cathyblog.domain.extend.OptionKey.*;
import static com.cathy.cathyblog.domain.extend.OptionKey.CATEGORY_C_STATISTIC;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    OptionRepository optionRepository;
    private static Logger logger = LoggerFactory.getLogger(StatisticServiceImpl.class);

    @Override
    public void incStatistic(String key) throws RepositoryException {
        final Option statistic = optionRepository.getByOptionKey(key);
        if (null == statistic) {
            throw new RepositoryException("Not found statistic");
        }
        Integer count=Integer.parseInt(statistic.getOptionValue())+1;
        statistic.setOptionValue(count.toString());
        optionRepository.save(statistic);
    }
    @Override
    public void initStatistic() {
        logger.debug("Initializing statistic....");

        final Option statisticBlogArticleCountOpt = new Option();
        statisticBlogArticleCountOpt.setOptionKey(ID_C_STATISTIC_BLOG_ARTICLE_COUNT);
        statisticBlogArticleCountOpt.setOptionValue("0");
        statisticBlogArticleCountOpt.setOptionCategory(CATEGORY_C_STATISTIC);
        optionRepository.save(statisticBlogArticleCountOpt);

        final Option statisticBlogCommentCountOpt = new Option();
        statisticBlogCommentCountOpt.setOptionKey(ID_C_STATISTIC_BLOG_COMMENT_COUNT);
        statisticBlogCommentCountOpt.setOptionValue("0");
        statisticBlogCommentCountOpt.setOptionCategory(CATEGORY_C_STATISTIC);
        optionRepository.save(statisticBlogCommentCountOpt);

        final Option statisticBlogViewCountOpt = new Option();
        statisticBlogViewCountOpt.setOptionKey(ID_C_STATISTIC_BLOG_VIEW_COUNT);
        statisticBlogViewCountOpt.setOptionValue("0");
        statisticBlogViewCountOpt.setOptionCategory(CATEGORY_C_STATISTIC);
        optionRepository.save(statisticBlogViewCountOpt);

        final Option statisticPublishedBlogArticleCountOpt = new Option();
        statisticPublishedBlogArticleCountOpt.setOptionKey(ID_C_STATISTIC_PUBLISHED_ARTICLE_COUNT);
        statisticPublishedBlogArticleCountOpt.setOptionValue("0");
        statisticPublishedBlogArticleCountOpt.setOptionCategory(CATEGORY_C_STATISTIC);
        optionRepository.save(statisticPublishedBlogArticleCountOpt);

        final Option statisticPublishedBlogCommentCountOpt = new Option();
        statisticPublishedBlogCommentCountOpt.setOptionKey(ID_C_STATISTIC_PUBLISHED_BLOG_COMMENT_COUNT);
        statisticPublishedBlogCommentCountOpt.setOptionValue("0");
        statisticPublishedBlogCommentCountOpt.setOptionCategory(CATEGORY_C_STATISTIC);
        optionRepository.save(statisticPublishedBlogCommentCountOpt);

        logger.debug("Initialized statistic");
    }
}
