package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.common.consts.UserRoles;
import com.cathy.cathyblog.common.exceptions.ServiceException;
import com.cathy.cathyblog.domain.Option;
import com.cathy.cathyblog.domain.User;
import com.cathy.cathyblog.domain.extend.OptionKey;
import com.cathy.cathyblog.repository.UserRepository;
import com.cathy.cathyblog.service.InitService;
import com.cathy.cathyblog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService {
    /**
     * Maximum count of initialization.
     */
    private static final int MAX_RETRIES_CNT = 3;

    @Autowired
    UserRepository userRepository;
    @Autowired
    OptionService optionService;

    /**
     * 站点是否已经初始化
     *
     * @return
     */
    @Override
    public boolean isInited() {
        try {
            List<User> admins=userRepository.findAllByUserRole(UserRoles.ADMIN_ROLE);
            return admins!=null&&admins.size()>0;
        } catch (final Exception e) {
//            todo LOGGER.log(Level.WARN, "Solo has not been initialized");
            return false;
        }
    }

    /**
     * 执行站点初始化操作
     *
     * @param user 初始化管理员
     */
    @Override
    public void init(User user) throws ServiceException {
        if (isInited()) {
            return;
        }

        //todo LOGGER.log(Level.DEBUG, "Solo is running with database [{0}], creates all tables", Latkes.getRuntimeDatabase());

        int retries = MAX_RETRIES_CNT;

        while (true) {
            try {
                final Option statistic =optionService.getByOptionKey(OptionKey.ID_C_STATISTIC_BLOG_ARTICLE_COUNT);
                if (null == statistic) {
                    initStatistic();
                    //todo init
                    initPreference(requestJSONObject);
                    initReplyNotificationTemplate();
                    initAdmin(requestJSONObject);
                    initLink();
                }
                break;
            } catch (final Exception e) {
                if (0 == retries) {
                    //todo LOGGER.log(Level.ERROR, "Initialize error", e);
                    throw new ServiceException("Initailize Solo error: " + e.getMessage());
                }

                // Allow retry to occur
                --retries;
                //todo LOGGER.log(Level.WARN, "Retrying to init Solo[retries={0}]", retries);
            }
        }

        try {
            //todo init helloWorld();
        } catch (final Exception e) {
           //todo LOGGER.log(Level.ERROR, "Hello World error?!", e);
        }

        // todo init 同步作者
//        try {
//            HttpRequest.get(Latkes.getServePath() + "/blog/symphony/user").sendAsync();
//        } catch (final Exception e) {
//            LOGGER.log(Level.TRACE, "Sync account failed");
//        }

        //todo init加载插件 pluginManager.load();
    }

    private void initStatistic() {
        //todo LOGGER.debug("Initializing statistic....");

        final Option statisticBlogArticleCountOpt = new Option();
        statisticBlogArticleCountOpt.setOptionKey(OptionKey.ID_C_STATISTIC_BLOG_ARTICLE_COUNT);
        statisticBlogArticleCountOpt.setOptionValue("0");
        statisticBlogArticleCountOpt.setOptionCategory(OptionKey.CATEGORY_C_STATISTIC);
        optionService.add(statisticBlogArticleCountOpt);

        final Option statisticBlogCommentCountOpt = new Option();
        statisticBlogCommentCountOpt.setOptionKey(OptionKey.ID_C_STATISTIC_BLOG_COMMENT_COUNT);
        statisticBlogCommentCountOpt.setOptionValue( "0");
        statisticBlogCommentCountOpt.setOptionCategory( OptionKey.CATEGORY_C_STATISTIC);
        optionService.add(statisticBlogCommentCountOpt);

        final Option statisticBlogViewCountOpt = new Option();
        statisticBlogViewCountOpt.setOptionKey( OptionKey.ID_C_STATISTIC_BLOG_VIEW_COUNT);
        statisticBlogViewCountOpt.setOptionValue("0");
        statisticBlogViewCountOpt.setOptionCategory( OptionKey.CATEGORY_C_STATISTIC);
        optionService.add(statisticBlogViewCountOpt);

        final Option statisticPublishedBlogArticleCountOpt = new Option();
        statisticPublishedBlogArticleCountOpt.setOptionKey( OptionKey.ID_C_STATISTIC_PUBLISHED_ARTICLE_COUNT);
        statisticPublishedBlogArticleCountOpt.setOptionValue( "0");
        statisticPublishedBlogArticleCountOpt.setOptionCategory(OptionKey.CATEGORY_C_STATISTIC);
        optionService.add(statisticPublishedBlogArticleCountOpt);

        final Option statisticPublishedBlogCommentCountOpt = new Option();
        statisticPublishedBlogCommentCountOpt.setOptionKey( OptionKey.ID_C_STATISTIC_PUBLISHED_BLOG_COMMENT_COUNT);
        statisticPublishedBlogCommentCountOpt.setOptionValue( "0");
        statisticPublishedBlogCommentCountOpt.setOptionCategory( OptionKey.CATEGORY_C_STATISTIC);
        optionService.add(statisticPublishedBlogCommentCountOpt);

        //todo LOGGER.debug("Initialized statistic");
    }
}
