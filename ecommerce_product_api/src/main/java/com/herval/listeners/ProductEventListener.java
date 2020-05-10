package com.herval.listeners;

import com.herval.events.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @EventListener
    public void onApplicationEvent(ProductEvent productEvent) {
        log.info("Recebido evento do produto : " + productEvent.getEventType());
        log.info("Recebido evento do produto do Product Event : " + productEvent.getProduct().toString());
    }
}
