package com.cathy.cathyblog.controllers;

import com.cathy.cathyblog.common.exceptions.ServiceException;
import com.cathy.cathyblog.domain.Option;
import com.cathy.cathyblog.service.OptionService;
import com.cathy.cathyblog.service.StatisticService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    OptionService optionService;
    @Autowired
    StatisticService statisticService;
    @GetMapping(value = "/{currentPageNum}")
    public String index(@PathVariable Integer currentPageNum, HttpServletRequest request, HttpServletResponse response) {
        try {
            final List<Option> preference = optionService.getPreference();
            //todo 换肤
//            // https://github.com/b3log/solo/issues/12060
//            String specifiedSkin = Skins.getSkinDirName(request);
//            if (null != specifiedSkin) {
//                if ("default".equals(specifiedSkin)) {
//                    specifiedSkin = preference.optString(Option.ID_C_SKIN_DIR_NAME);
//
//                    final Cookie cookie = new Cookie(Skin.SKIN, null);
//                    cookie.setMaxAge(60 * 60); // 1 hour
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                }
//            } else {
//                specifiedSkin = preference.optString(Option.ID_C_SKIN_DIR_NAME);
//            }
//
//            final Set<String> skinDirNames = Skins.getSkinDirNames();
//            if (skinDirNames.contains(specifiedSkin)) {
//                Templates.MAIN_CFG.setServletContextForTemplateLoading(SoloServletListener.getServletContext(),
//                        "/skins/" + specifiedSkin);
//                request.setAttribute(Keys.TEMAPLTE_DIR_NAME, specifiedSkin);
//            }
//
//            final Set<String> skinDirNames = Skins.getSkinDirNames();
//            if (skinDirNames.contains(specifiedSkin)) {
//                Templates.MAIN_CFG.setServletContextForTemplateLoading(SoloServletListener.getServletContext(),
//                        "/skins/" + specifiedSkin);
//                request.setAttribute(Keys.TEMAPLTE_DIR_NAME, specifiedSkin);
//            }
////
////            Skins.fillLangs(preference.optString(Option.ID_C_LOCALE_STRING), (String) request.getAttribute(Keys.TEMAPLTE_DIR_NAME), dataModel);
////
//            filler.fillIndexArticles(request, dataModel, currentPageNum, preference);
//
//            filler.fillSide(request, dataModel, preference);
//            filler.fillBlogHeader(request, response, dataModel, preference);
//            filler.fillBlogFooter(request, dataModel, preference);
//
//            dataModel.put(Pagination.PAGINATION_CURRENT_PAGE_NUM, currentPageNum);
//            final int previousPageNum = currentPageNum > 1 ? currentPageNum - 1 : 0;
//            dataModel.put(Pagination.PAGINATION_PREVIOUS_PAGE_NUM, previousPageNum);
//
//            final Integer pageCount = (Integer) dataModel.get(Pagination.PAGINATION_PAGE_COUNT);
//            final int nextPageNum = currentPageNum + 1 > pageCount ? pageCount : currentPageNum + 1;
//            dataModel.put(Pagination.PAGINATION_NEXT_PAGE_NUM, nextPageNum);
//
//            dataModel.put(Common.PATH, "");
//
//            statisticService.incStatistic(OptionKey.ID_C_STATISTIC_BLOG_VIEW_COUNT);
////
////            // https://github.com/b3log/solo/issues/12060
////            if (!preference.optString(Skin.SKIN_DIR_NAME).equals(specifiedSkin) && !Requests.mobileRequest(request)) {
////                final Cookie cookie = new Cookie(Skin.SKIN, specifiedSkin);
////                cookie.setMaxAge(60 * 60); // 1 hour
////                cookie.setPath("/");
////                response.addCookie(cookie);
////            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
