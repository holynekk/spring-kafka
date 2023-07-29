package com.demo.kafka.producer;

import com.demo.kafka.entity.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(FoodOrder foodOrder) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(foodOrder);
        kafkaTemplate.send("t-food-order", json);
    }
}
