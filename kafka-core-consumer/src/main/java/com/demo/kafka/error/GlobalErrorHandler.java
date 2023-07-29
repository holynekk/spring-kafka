package com.demo.kafka.error;

import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;

public class GlobalErrorHandler implements ConsumerAwareListenerErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
        LOG.warn("Global error handler for message: {}", message.getPayload().toString());
        return null;
    }
}
