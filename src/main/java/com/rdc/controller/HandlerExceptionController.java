package com.rdc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class HandlerExceptionController {
    /**
     * Created by Ning
     * time 2018/7/25 19:51
     * 处理异常
     * @param ex
     * @return
     */
    //@ExceptionHandler({NullPointerException.class})
    public void NullPointerException(NullPointerException ex) {

    }
}
