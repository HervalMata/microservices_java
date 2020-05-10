package com.herval.spring.controller;

import com.herval.exceptions.HTTP400Exception;
import com.herval.exceptions.HTTP404Exception;
import com.herval.exceptions.RestAPIExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController implements ApplicationEventPublisherAware {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;
    protected static final String DEFAULT_PAGE_SIZE = "20";
    protected static final String DEFAULT_PAGE_NUMBER = "0";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HTTP400Exception.class)
    public @ResponseBody RestAPIExceptionInfo handleBadRequestException(HTTP400Exception ex, WebRequest request, HttpServletResponse response)
    {
        log.info("Received Bad Request Exception" + ex.getLocalizedMessage());
        return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "A Requisição não tem os parâmetros corretos");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HTTP404Exception.class)
    public @ResponseBody RestAPIExceptionInfo handleBadRequestException(HTTP404Exception ex, WebRequest request, HttpServletResponse response)
    {
        log.info("Received Request Not Found Exception" + ex.getLocalizedMessage());
        return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "A Requisição não foi encontrada");
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new HTTP404Exception("Recurso não encontrado.");
        }
        return resource;
    }
}
