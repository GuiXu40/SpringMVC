package org.Converter.binging;

import org.Converter.converter.DateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

//实现WebBindingInitializer接口
public class DateBindingInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder webDataBinder, WebRequest webRequest) {
        //注册自定义编辑器
        webDataBinder.registerCustomEditor(Date.class,new DateEditor());
    }
}
