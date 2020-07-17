package com.test.coding.pathfinder.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PathFinderExceptionHandler {

    @ExceptionHandler(PathFinderException.class)
    public @ResponseBody
    String handleResourceNotFound(final PathFinderException exception){
        return exception.getMessage();
    }
}
