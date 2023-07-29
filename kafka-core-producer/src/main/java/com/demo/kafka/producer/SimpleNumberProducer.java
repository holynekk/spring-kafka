package com.demo.kafka.producer;

import com.demo.kafka.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimpleNumberProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void send(SimpleNumber simpleNumber) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(simpleNumber);
        kafkaTemplate.send("t-simple-number", json);
    }
}
