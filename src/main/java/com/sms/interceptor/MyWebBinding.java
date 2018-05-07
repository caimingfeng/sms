package com.sms.interceptor;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Created by ${lrj} on 2016/5/23.
 * Don't ask me anything  I don't know anything  Don't  look   Find by Youself
 */
public class MyWebBinding implements WebBindingInitializer {

    public void initBinder(WebDataBinder binder, WebRequest request) {

        binder.registerCustomEditor(Date.class, new DateConvertEditor());
    }

}
