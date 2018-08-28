package com.cathy.cathyblog.controllers;

import com.cathy.cathyblog.common.ApiBaseModel;
import com.cathy.cathyblog.common.consts.SiteConfig;
import com.cathy.cathyblog.common.util.StringUtil;
import com.cathy.cathyblog.domain.User;
import com.cathy.cathyblog.domain.extend.UserExtend;
import com.cathy.cathyblog.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 站点初始化
 */
@Controller
public class InitController {
    @Autowired
    InitService initService;

    @GetMapping(value = "/init")
    public String showInit(Model model){
        if (initService.isInited()) {
            return "redirect:/";
        }
        model.addAttribute("siteurl",SiteConfig.SITE_URL);
        User user=new User();
        model.addAttribute("user",user);

// todo init
//        final Map<String, Object> dataModel = renderer.getDataModel();
//
//        dataModel.put(Common.VERSION, SoloServletListener.VERSION);
//        dataModel.put(Common.STATIC_RESOURCE_VERSION, Latkes.getStaticResourceVersion());
//        dataModel.put(Common.YEAR, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
//
//        Keys.fillRuntime(dataModel);
//        filler.fillMinified(dataModel);
        return "init";
    }

    @PostMapping(value = "/init")
    @ResponseBody
    public ApiBaseModel<Integer> initSolo(User user) {
        ApiBaseModel<Integer> result=new ApiBaseModel<Integer>();
        if (initService.isInited()) {
            result.setReturncode(-1);
            result.setMessage("站点已经初始化");
            return result;
        }

        try {
            if (StringUtil.isEmptyOrNull(user.getUserName()) || StringUtil.isEmptyOrNull(user.getUserEmail()) || StringUtil.isEmptyOrNull(user.getUserPassword())
                    || !StringUtil.isEmail(user.getUserEmail())) {
                result.setReturncode(-2);
                result.setMessage("初始化失败，请检查输入值！");
                return result;
            }

            if (UserExtend.invalidUserName(user.getUserName())) {
                result.setReturncode(-3);
                result.setMessage("初始化失败，请检查用户名！长度1-20，由a-z, A-Z, 0-9组成，不要包含admin");
                return result;
            }

            initService.init(user);

            // todo If initialized, login the admin
//            final JSONObject admin = new JSONObject();
////            admin.put(User.USER_NAME, userName);
////            admin.put(User.USER_EMAIL, userEmail);
////            admin.put(User.USER_ROLE, Role.ADMIN_ROLE);
////            admin.put(User.USER_PASSWORD, userPassword);
////            admin.put(UserExt.USER_AVATAR, Thumbnails.getGravatarURL(userEmail, "128"));
////
////            Sessions.login(request, response, admin);

            result.setReturncode(0);
            result.setMessage("初始化成功");
            result.setResult(1);
            return result;
        } catch (final Exception e) {
            //todo LOGGER.log(Level.ERROR, e.getMessage(), e);
            result.setReturncode(-4);
            result.setMessage(e.getMessage());
            return result;
        }
    }
}