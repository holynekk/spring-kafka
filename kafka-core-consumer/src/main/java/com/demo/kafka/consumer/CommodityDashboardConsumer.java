package com.demo.kafka.consumer;

import com.demo.kafka.entity.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

//@Service
public class CommodityDashboardConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(CommodityDashboardConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-commodity", groupId = "cg-dashboard")
    public void consume(String message) throws JsonProcessingException, InterruptedException {
        Commodity commodity = objectMapper.readValue(message, Commodity.class);
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(500, 2000));
        LOG.info("Dashboard logic for: {}", commodity);
    }

}
