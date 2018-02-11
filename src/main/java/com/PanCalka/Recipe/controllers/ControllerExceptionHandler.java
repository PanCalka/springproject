package com.PanCalka.Recipe.controllers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ControllerExceptionHandler {

    static final Logger logger = Logger.getLogger(ControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArg(Exception exp) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("illegalError");
        modelAndView.addObject("exception", exp);
        logger.error(exp.getMessage());
        return modelAndView;
    }
}
