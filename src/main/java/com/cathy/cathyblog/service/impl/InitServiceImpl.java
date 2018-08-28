package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.common.consts.UserRoles;
import com.cathy.cathyblog.common.exceptions.ServiceException;
import com.cathy.cathyblog.common.util.IdUtil;
import com.cathy.cathyblog.common.util.ThumbnailUtil;
import com.cathy.cathyblog.domain.Link;
import com.cathy.cathyblog.domain.Option;
import com.cathy.cathyblog.domain.User;
import com.cathy.cathyblog.repository.UserRepository;
import com.cathy.cathyblog.service.InitService;
import com.cathy.cathyblog.service.LinkService;
import com.cathy.cathyblog.service.OptionService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cathy.cathyblog.domain.extend.OptionKey.*;

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
    @Autowired
    LinkService linkService;
    private static Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);

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
            logger.warn("站点已经执行过初始化");
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

        int retries = MAX_RETRIES_CNT;

        while (true) {
            try {
                final Option statistic =optionService.getByOptionKey(ID_C_STATISTIC_BLOG_ARTICLE_COUNT);
                if (null == statistic) {
                    initStatistic();
                    initPreference(user);
                    initReplyNotificationTemplate();
                    initAdmin(user);
                    initLink();
                }
                break;
            } catch (final Exception e) {
                if (0 == retries) {
                    logger.error("Initialize error", e);
                    throw new ServiceException("Initailize Solo error: " + e.getMessage());
                }

                // Allow retry to occur
                --retries;
                logger.warn("Retrying to init Solo[retries={0}]", retries);
            }
        }

        try {
//            helloWorld();
        } catch (final Exception e) {
          logger.error( "Hello World error?!", e);
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
       logger.debug("Initializing statistic....");

        final Option statisticBlogArticleCountOpt = new Option();
        statisticBlogArticleCountOpt.setOptionKey(ID_C_STATISTIC_BLOG_ARTICLE_COUNT);
        statisticBlogArticleCountOpt.setOptionValue("0");
        statisticBlogArticleCountOpt.setOptionCategory(CATEGORY_C_STATISTIC);
        optionService.add(statisticBlogArticleCountOpt);

        final Option statisticBlogCommentCountOpt = new Option();
        statisticBlogCommentCountOpt.setOptionKey(ID_C_STATISTIC_BLOG_COMMENT_COUNT);
        statisticBlogCommentCountOpt.setOptionValue( "0");
        statisticBlogCommentCountOpt.setOptionCategory( CATEGORY_C_STATISTIC);
        optionService.add(statisticBlogCommentCountOpt);

        final Option statisticBlogViewCountOpt = new Option();
        statisticBlogViewCountOpt.setOptionKey( ID_C_STATISTIC_BLOG_VIEW_COUNT);
        statisticBlogViewCountOpt.setOptionValue("0");
        statisticBlogViewCountOpt.setOptionCategory( CATEGORY_C_STATISTIC);
        optionService.add(statisticBlogViewCountOpt);

        final Option statisticPublishedBlogArticleCountOpt = new Option();
        statisticPublishedBlogArticleCountOpt.setOptionKey( ID_C_STATISTIC_PUBLISHED_ARTICLE_COUNT);
        statisticPublishedBlogArticleCountOpt.setOptionValue( "0");
        statisticPublishedBlogArticleCountOpt.setOptionCategory(CATEGORY_C_STATISTIC);
        optionService.add(statisticPublishedBlogArticleCountOpt);

        final Option statisticPublishedBlogCommentCountOpt = new Option();
        statisticPublishedBlogCommentCountOpt.setOptionKey( ID_C_STATISTIC_PUBLISHED_BLOG_COMMENT_COUNT);
        statisticPublishedBlogCommentCountOpt.setOptionValue( "0");
        statisticPublishedBlogCommentCountOpt.setOptionCategory( CATEGORY_C_STATISTIC);
        optionService.add(statisticPublishedBlogCommentCountOpt);

        logger.debug("Initialized statistic");
    }

    private void initPreference(User user) {
        logger.debug("Initializing preference....");

        final Option noticeBoardOpt = new Option();
        noticeBoardOpt.setOptionKey(ID_C_NOTICE_BOARD);
        noticeBoardOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        noticeBoardOpt.setOptionValue( DefaultPreference.DEFAULT_NOTICE_BOARD);
        optionService.add(noticeBoardOpt);

        final Option metaDescriptionOpt = new Option();
        metaDescriptionOpt.setOptionKey(ID_C_META_DESCRIPTION);
        metaDescriptionOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        metaDescriptionOpt.setOptionValue( DefaultPreference.DEFAULT_META_DESCRIPTION);
        optionService.add(metaDescriptionOpt);

        final Option metaKeywordsOpt = new Option();
        metaKeywordsOpt.setOptionKey(ID_C_META_KEYWORDS);
        metaKeywordsOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        metaKeywordsOpt.setOptionValue( DefaultPreference.DEFAULT_META_KEYWORDS);
        optionService.add(metaKeywordsOpt);

        final Option htmlHeadOpt = new Option();
        htmlHeadOpt.setOptionKey(ID_C_HTML_HEAD);
        htmlHeadOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        htmlHeadOpt.setOptionValue( DefaultPreference.DEFAULT_HTML_HEAD);
        optionService.add(htmlHeadOpt);

        final Option relevantArticlesDisplayCountOpt = new Option();
        relevantArticlesDisplayCountOpt.setOptionKey(ID_C_RELEVANT_ARTICLES_DISPLAY_CNT);
        relevantArticlesDisplayCountOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        relevantArticlesDisplayCountOpt.setOptionValue( DefaultPreference.DEFAULT_RELEVANT_ARTICLES_DISPLAY_COUNT);
        optionService.add(relevantArticlesDisplayCountOpt);

        final Option randomArticlesDisplayCountOpt = new Option();
        randomArticlesDisplayCountOpt.setOptionKey(ID_C_RANDOM_ARTICLES_DISPLAY_CNT);
        randomArticlesDisplayCountOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        randomArticlesDisplayCountOpt.setOptionValue( DefaultPreference.DEFAULT_RANDOM_ARTICLES_DISPLAY_COUNT);
        optionService.add(randomArticlesDisplayCountOpt);

        final Option externalRelevantArticlesDisplayCountOpt = new Option();
        externalRelevantArticlesDisplayCountOpt.setOptionKey(ID_C_EXTERNAL_RELEVANT_ARTICLES_DISPLAY_CNT);
        externalRelevantArticlesDisplayCountOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        externalRelevantArticlesDisplayCountOpt.setOptionValue( DefaultPreference.DEFAULT_EXTERNAL_RELEVANT_ARTICLES_DISPLAY_COUNT);
        optionService.add(externalRelevantArticlesDisplayCountOpt);

        final Option mostViewArticleDisplayCountOpt = new Option();
        mostViewArticleDisplayCountOpt.setOptionKey(ID_C_MOST_VIEW_ARTICLE_DISPLAY_CNT);
        mostViewArticleDisplayCountOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        mostViewArticleDisplayCountOpt.setOptionValue( DefaultPreference.DEFAULT_MOST_VIEW_ARTICLES_DISPLAY_COUNT);
        optionService.add(mostViewArticleDisplayCountOpt);

        final Option articleListDisplayCountOpt = new Option();
        articleListDisplayCountOpt.setOptionKey(ID_C_ARTICLE_LIST_DISPLAY_COUNT);
        articleListDisplayCountOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        articleListDisplayCountOpt.setOptionValue(DefaultPreference.DEFAULT_ARTICLE_LIST_DISPLAY_COUNT);
        optionService.add(articleListDisplayCountOpt);

        final Option articleListPaginationWindowSizeOpt = new Option();
        articleListPaginationWindowSizeOpt.setOptionKey(ID_C_ARTICLE_LIST_PAGINATION_WINDOW_SIZE);
        articleListPaginationWindowSizeOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        articleListPaginationWindowSizeOpt.setOptionValue( DefaultPreference.DEFAULT_ARTICLE_LIST_PAGINATION_WINDOW_SIZE);
        optionService.add(articleListPaginationWindowSizeOpt);

        final Option mostUsedTagDisplayCountOpt = new Option();
        mostUsedTagDisplayCountOpt.setOptionKey(ID_C_MOST_USED_TAG_DISPLAY_CNT);
        mostUsedTagDisplayCountOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        mostUsedTagDisplayCountOpt.setOptionValue( DefaultPreference.DEFAULT_MOST_USED_TAG_DISPLAY_COUNT);
        optionService.add(mostUsedTagDisplayCountOpt);

        final Option mostCommentArticleDisplayCountOpt = new Option();
        mostCommentArticleDisplayCountOpt.setOptionKey(ID_C_MOST_COMMENT_ARTICLE_DISPLAY_CNT);
        mostCommentArticleDisplayCountOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        mostCommentArticleDisplayCountOpt.setOptionValue( DefaultPreference.DEFAULT_MOST_COMMENT_ARTICLE_DISPLAY_COUNT);
        optionService.add(mostCommentArticleDisplayCountOpt);

        final Option recentArticleDisplayCountOpt = new Option();
        recentArticleDisplayCountOpt.setOptionKey(ID_C_RECENT_ARTICLE_DISPLAY_CNT);
        recentArticleDisplayCountOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        recentArticleDisplayCountOpt.setOptionValue( DefaultPreference.DEFAULT_RECENT_ARTICLE_DISPLAY_COUNT);
        optionService.add(recentArticleDisplayCountOpt);

        final Option recentCommentDisplayCountOpt = new Option();
        recentCommentDisplayCountOpt.setOptionKey(ID_C_RECENT_COMMENT_DISPLAY_CNT);
        recentCommentDisplayCountOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        recentCommentDisplayCountOpt.setOptionValue( DefaultPreference.DEFAULT_RECENT_COMMENT_DISPLAY_COUNT);
        optionService.add(recentCommentDisplayCountOpt);

        final Option blogTitleOpt = new Option();
        blogTitleOpt.setOptionKey(ID_C_BLOG_TITLE);
        blogTitleOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        blogTitleOpt.setOptionValue( user.getUserName() + " 的个人博客");
        optionService.add(blogTitleOpt);

        final Option blogSubtitleOpt = new Option();
        blogSubtitleOpt.setOptionKey(ID_C_BLOG_SUBTITLE);
        blogSubtitleOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        blogSubtitleOpt.setOptionValue( DefaultPreference.DEFAULT_BLOG_SUBTITLE);
        optionService.add(blogSubtitleOpt);

        final Option adminEmailOpt = new Option();
        adminEmailOpt.setOptionKey(ID_C_ADMIN_EMAIL);
        adminEmailOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        adminEmailOpt.setOptionValue( user.getUserEmail());
        optionService.add(adminEmailOpt);

        final Option localeStringOpt = new Option();
        localeStringOpt.setOptionKey(ID_C_LOCALE_STRING);
        localeStringOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        localeStringOpt.setOptionValue( DefaultPreference.DEFAULT_LANGUAGE);
        optionService.add(localeStringOpt);

        final Option enableArticleUpdateHintOpt = new Option();
        enableArticleUpdateHintOpt.setOptionKey(ID_C_ENABLE_ARTICLE_UPDATE_HINT);
        enableArticleUpdateHintOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        enableArticleUpdateHintOpt.setOptionValue( DefaultPreference.DEFAULT_ENABLE_ARTICLE_UPDATE_HINT);
        optionService.add(enableArticleUpdateHintOpt);

        final Option signsOpt = new Option();
        signsOpt.setOptionKey(ID_C_SIGNS);
        signsOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        signsOpt.setOptionValue( DefaultPreference.DEFAULT_SIGNS);
        optionService.add(signsOpt);

        final Option timeZoneIdOpt = new Option();
        timeZoneIdOpt.setOptionKey(ID_C_TIME_ZONE_ID);
        timeZoneIdOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        timeZoneIdOpt.setOptionValue( DefaultPreference.DEFAULT_TIME_ZONE);
        optionService.add(timeZoneIdOpt);

        final Option allowVisitDraftViaPermalinkOpt = new Option();
        allowVisitDraftViaPermalinkOpt.setOptionKey(ID_C_ALLOW_VISIT_DRAFT_VIA_PERMALINK);
        allowVisitDraftViaPermalinkOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        allowVisitDraftViaPermalinkOpt.setOptionValue( DefaultPreference.DEFAULT_ALLOW_VISIT_DRAFT_VIA_PERMALINK);
        optionService.add(allowVisitDraftViaPermalinkOpt);

        final Option allowRegisterOpt = new Option();
        allowRegisterOpt.setOptionKey(ID_C_ALLOW_REGISTER);
        allowRegisterOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        allowRegisterOpt.setOptionValue( DefaultPreference.DEFAULT_ALLOW_REGISTER);
        optionService.add(allowRegisterOpt);

        final Option commentableOpt = new Option();
        commentableOpt.setOptionKey(ID_C_COMMENTABLE);
        commentableOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        commentableOpt.setOptionValue( DefaultPreference.DEFAULT_COMMENTABLE);
        optionService.add(commentableOpt);

//        final Option versionOpt = new Option();
//        versionOpt.setOptionKey(ID_C_VERSION);
//        versionOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
//        versionOpt.setOptionValue( SoloServletListener.VERSION);
//        optionService.add(versionOpt);

        final Option articleListStyleOpt = new Option();
        articleListStyleOpt.setOptionKey(ID_C_ARTICLE_LIST_STYLE);
        articleListStyleOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        articleListStyleOpt.setOptionValue( DefaultPreference.DEFAULT_ARTICLE_LIST_STYLE);
        optionService.add(articleListStyleOpt);

        final Option keyOfSoloOpt = new Option();
        keyOfSoloOpt.setOptionKey(ID_C_KEY_OF_SOLO);
        keyOfSoloOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        keyOfSoloOpt.setOptionValue( IdUtil.genTimeMillisId());
        optionService.add(keyOfSoloOpt);

        final Option feedOutputModeOpt = new Option();
        feedOutputModeOpt.setOptionKey(ID_C_FEED_OUTPUT_MODE);
        feedOutputModeOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        feedOutputModeOpt.setOptionValue( DefaultPreference.DEFAULT_FEED_OUTPUT_MODE);
        optionService.add(feedOutputModeOpt);

        final Option feedOutputCntOpt = new Option();
        feedOutputCntOpt.setOptionKey(ID_C_FEED_OUTPUT_CNT);
        feedOutputCntOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        feedOutputCntOpt.setOptionValue( DefaultPreference.DEFAULT_FEED_OUTPUT_CNT);
        optionService.add(feedOutputCntOpt);

        final Option editorTypeOpt = new Option();
        editorTypeOpt.setOptionKey(ID_C_EDITOR_TYPE);
        editorTypeOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        editorTypeOpt.setOptionValue( DefaultPreference.DEFAULT_EDITOR_TYPE);
        optionService.add(editorTypeOpt);

        final Option footerContentOpt = new Option();
        footerContentOpt.setOptionKey(ID_C_FOOTER_CONTENT);
        footerContentOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        footerContentOpt.setOptionValue( DefaultPreference.DEFAULT_FOOTER_CONTENT);
        optionService.add(footerContentOpt);

        final String skinDirName = DefaultPreference.DEFAULT_SKIN_DIR_NAME;
        final Option skinDirNameOpt = new Option();
        skinDirNameOpt.setOptionKey(ID_C_SKIN_DIR_NAME);
        skinDirNameOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
        skinDirNameOpt.setOptionValue( skinDirName);
        optionService.add(skinDirNameOpt);

        //todo init加载皮肤目录
//        final String skinName = Latkes.getSkinName(skinDirName);
//        final Option skinNameOpt = new Option();
//        skinNameOpt.setOptionKey(ID_C_SKIN_NAME);
//        skinNameOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
//        skinNameOpt.setOptionValue( skinName);
//        optionService.add(skinNameOpt);
//
//        final Set<String> skinDirNames = Skins.getSkinDirNames();
//        final JSONArray skinArray = new JSONArray();
//        for (final String dirName : skinDirNames) {
//            final Option skin = new Option();
//            skinArray.put(skin);
//
//            final String name = Latkes.getSkinName(dirName);
//            skin.put(Skin.SKIN_NAME, name);
//            skin.put(Skin.SKIN_DIR_NAME, dirName);
//        }
//
//        final Option skinsOpt = new Option();
//        skinsOpt.setOptionKey(ID_C_SKINS);
//        skinsOpt.setOptionCategory(CATEGORY_C_PREFERENCE);
//        skinsOpt.setOptionValue( skinArray.toString());
//        optionService.add(skinsOpt);

        //todo init template config dir
//        final ServletContext servletContext = SoloServletListener.getServletContext();
//
//        Templates.MAIN_CFG.setServletContextForTemplateLoading(servletContext, "/skins/" + skinDirName);


        logger.debug("Initialized preference");
    }

    private void initReplyNotificationTemplate() throws Exception {
       logger.debug("Initializing reply notification template");

        //todo init replyNotificationTemplate
//        final JSONObject replyNotificationTemplate = new JSONObject(DefaultPreference.DEFAULT_REPLY_NOTIFICATION_TEMPLATE);
//        replyNotificationTemplate.put(Keys.OBJECT_ID, "replyNotificationTemplate");
//
//        final Option subjectOpt = new Option();
//        subjectOpt.setOptionKey(OptionKey.ID_C_REPLY_NOTI_TPL_SUBJECT);
//        subjectOpt.setOptionCategory(OptionKey.CATEGORY_C_PREFERENCE);
//        subjectOpt.setOptionValue(replyNotificationTemplate.optString("subject"));
//        optionService.add(subjectOpt);
//
//        final Option bodyOpt = new Option();
//        bodyOpt.setOptionKey(OptionKey.ID_C_REPLY_NOTI_TPL_BODY);
//        bodyOpt.setOptionCategory(OptionKey.CATEGORY_C_PREFERENCE);
//        bodyOpt.setOptionCategory(replyNotificationTemplate.optString("body"));
//        optionService.add(bodyOpt);

       logger.debug("Initialized reply notification template");
    }

    private void initAdmin(User admin) throws Exception {
        logger.debug("Initializing admin....");
//        admin.setUserURL(Latkes.getServePath());
        admin.setUserRole(UserRoles.ADMIN_ROLE);
        admin.setUserPassword( DigestUtils.md5Hex(admin.getUserPassword()));
        admin.setUserArticleCount( 0);
        admin.setUserPublishedArticleCount( 0);
        admin.setUserAvatar(ThumbnailUtil.getGravatarURL(admin.getUserEmail(), "128"));

        userRepository.save(admin);

        logger.debug("Initialized admin");
    }

    private void initLink() throws Exception {
        Link link=new Link();
        link.setLinkTitle("博客园-陈敬");
        link.setLinkAddress("https://www.cnblogs.com/janes");
        link.setLinkDescription("博客园-陈敬");

        final int maxOrder = linkService.queryMaxOrder();
        link.setLinkOrder(maxOrder+1);

        linkService.save(link);
    }
}
