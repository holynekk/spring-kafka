package com.demo.kafka.consumer;

import com.demo.kafka.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class ImageConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(ImageConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-image", containerFactory = "imageRetryContainerFactory", concurrency = "2")
    public void consume(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        Image img = objectMapper.readValue(consumerRecord.value(), Image.class);

        if (img.getType().equalsIgnoreCase("svg")) {
            LOG.warn("Throwing exception on partition {} for image {}", consumerRecord.partition(), img);
            throw new IllegalArgumentException("Simulate API call failed!");
        }
        LOG.info("Processing on partition {} for image {}", consumerRecord.partition(), img);
    }
}
