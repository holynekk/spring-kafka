package com.demo.kafka.consumer;

import com.demo.kafka.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SimpleNumberConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleNumber.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-simple-number")
    public void consume(String message) throws JsonProcessingException {
        SimpleNumber simpleNumber = objectMapper.readValue(message, SimpleNumber.class);

        if (simpleNumber.getNumber() %2 != 0) {
            throw new IllegalArgumentException("Odd number: " + simpleNumber.getNumber());
        }

        LOG.info("Processing simple number: " + simpleNumber);
    }
}
