package com.demo.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FixedRateConsumer2 {
    private static final Logger LOG = LoggerFactory.getLogger(FixedRateConsumer2.class);

    @KafkaListener(topics = "t-fixedrate-2")
    public void consume(String message) {
        LOG.info("Consuming: {}", message);
    }
}
