package com.cathy.cathyblog.controllers.util;

import com.cathy.cathyblog.common.consts.CommonConst;
import com.cathy.cathyblog.common.exceptions.RepositoryException;
import com.cathy.cathyblog.common.exceptions.ServiceException;
import com.cathy.cathyblog.common.util.StringUtil;
import com.cathy.cathyblog.domain.extend.OptionKey;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class Fill {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Fill.class);
    public void fillBlogHeader(Model model, Map<String,String> preference)
            throws ServiceException {
        try {
            logger.debug("Filling header....");
            //todo topbar
//            final String topBarHTML = topBars.getTopBarHTML(request, response);
//            model.addAttribute(Common.LOGIN_URL, userQueryService.getLoginURL(Common.ADMIN_INDEX_URI));
//            model.addAttribute(Common.LOGOUT_URL, userQueryService.getLogoutURL());
//            model.addAttribute(Common.ONLINE_VISITOR_CNT, StatisticQueryService.getOnlineVisitorCount());
//            model.addAttribute(Common.TOP_BAR, topBarHTML);
            //meta keywords
            String metaKeywords =""; ;
            if (preference.containsKey(OptionKey.ID_C_META_KEYWORDS)) {
                metaKeywords = preference.get(OptionKey.ID_C_META_KEYWORDS);
            }
            model.addAttribute(OptionKey.ID_C_META_KEYWORDS, metaKeywords);
            //meta description
            String metaDescription = preference.get(OptionKey.ID_C_META_DESCRIPTION);
            if (preference.containsKey(OptionKey.ID_C_META_DESCRIPTION)) {
                metaDescription = preference.get(OptionKey.ID_C_META_DESCRIPTION);
            }
            model.addAttribute(OptionKey.ID_C_META_DESCRIPTION, metaDescription);

            model.addAttribute(OptionKey.ID_C_BLOG_TITLE, preference.get(OptionKey.ID_C_BLOG_TITLE));
            model.addAttribute(OptionKey.ID_C_BLOG_SUBTITLE, preference.get(OptionKey.ID_C_BLOG_SUBTITLE));
//
//            model.addAttribute(OptionKey.ID_C_ARTICLE_LIST_DISPLAY_COUNT, Integer.parseInt(preference.get(OptionKey.ID_C_ARTICLE_LIST_DISPLAY_COUNT)));
//            model.addAttribute(OptionKey.ID_C_ARTICLE_LIST_PAGINATION_WINDOW_SIZE,Integer.parseInt(preference.get(OptionKey.ID_C_ARTICLE_LIST_PAGINATION_WINDOW_SIZE)));
//            model.addAttribute(OptionKey.ID_C_LOCALE_STRING, preference.get(OptionKey.ID_C_LOCALE_STRING));
            model.addAttribute(OptionKey.ID_C_HTML_HEAD, preference.get(OptionKey.ID_C_HTML_HEAD));
//
//
//
//
//            model.addAttribute(CommonConst.YEAR, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
//            //todo islogin
////            model.addAttribute(CommonConst.IS_LOGGED_IN, null != userQueryService.getCurrentUser(request));
////            model.addAttribute(CommonConst.FAVICON_API, Solos.FAVICON_API);
//
//            final String noticeBoard = preference.get(OptionKey.ID_C_NOTICE_BOARD);
//
//            model.addAttribute(OptionKey.ID_C_NOTICE_BOARD, noticeBoard);
//
//            final Query query = new Query().setPageCount(1);
//            final JSONObject result = userRepository.get(query);
//            final JSONArray users = result.getJSONArray(Keys.RESULTS);
//            final List<JSONObject> userList = CollectionUtils.jsonArrayToList(users);
//            model.addAttribute(User.USERS, userList);
//
//            final JSONObject admin = userRepository.getAdmin();
//            model.addAttribute(Common.ADMIN_USER, admin);
//
//            final String skinDirName = (String) request.getAttribute(Keys.TEMAPLTE_DIR_NAME);
//
//            model.addAttribute(Skin.SKIN_DIR_NAME, skinDirName);
//
//            Keys.fillRuntime(model);
//            fillMinified(model);
//            fillPageNavigations(model);
//            fillStatistic(model);
//
//            fillMostUsedTags(model, preference);
//            fillArchiveDates(model, preference);
//            fillMostUsedCategories(model, preference);
        } catch (final Exception e) {
            logger.error( "Fills blog header failed", e);
            throw new ServiceException(e);
        }
    }
}
