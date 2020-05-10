package com.herval.aspects;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestControllerAspect {

    private final Logger logger = LoggerFactory.getLogger("RestControllerAspect");
    Counter productCreatedCounter = Metrics.counter("com.herval.product.created");

    @Before("execution(public * com.herval.spring.controller.*Controller.*(..))")
    public void generalAllMethodAspect() {
        logger.info("Todos os métodos chamados invocaram o método de aspecto geral");
    }

    @AfterReturning("execution(public * com.herval.spring.controller.*Controller.createdProduct(..))")
    public void getsCalledOnProductSave() {
        logger.info("O Aspecto é retornado quando o método salvar do controller é chamado.");
        productCreatedCounter.increment();
    }
}
