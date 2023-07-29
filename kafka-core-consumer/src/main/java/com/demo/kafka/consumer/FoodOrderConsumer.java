package com.demo.kafka.consumer;

import com.demo.kafka.entity.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(FoodOrderConsumer.class);

    private static final int MAX_ORDER_AMOUNT = 7;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-food-order", errorHandler = "myFoodOrderErrorHandler")
    public void consume(String message) throws JsonProcessingException {
        FoodOrder order = objectMapper.readValue(message, FoodOrder.class);

        if (order.getAmount() > MAX_ORDER_AMOUNT) {
            throw new IllegalArgumentException("Order amount is too much: " + order.getAmount());
        }

        LOG.info("Processing food order: " + order);
    }
}
