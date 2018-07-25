package com.rdc.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandlerException {

    /**
     * Created by Ning
     * time 2018/7/25 19:51
     * 处理异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({NullPointerException.class})
    public ModelAndView nullPointException(Exception ex) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        return mv;
    }


}
