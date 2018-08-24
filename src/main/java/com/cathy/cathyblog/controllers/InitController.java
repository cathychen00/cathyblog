package com.cathy.cathyblog.controllers;

import com.cathy.cathyblog.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 站点初始化
 */
@Controller
public class InitController {
    @Autowired
    InitService initService;

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    public String showInit(){
        if (initService.isInited()) {
            return "redirect:/";
        }

// todo init
//        final Map<String, Object> dataModel = renderer.getDataModel();
//
//        final Map<String, String> langs = langPropsService.getAll(Locales.getLocale(request));
//
//        dataModel.putAll(langs);
//
//        dataModel.put(Common.VERSION, SoloServletListener.VERSION);
//        dataModel.put(Common.STATIC_RESOURCE_VERSION, Latkes.getStaticResourceVersion());
//        dataModel.put(Common.YEAR, String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
//
//        Keys.fillRuntime(dataModel);
//        filler.fillMinified(dataModel);
        return "init";
    }
}