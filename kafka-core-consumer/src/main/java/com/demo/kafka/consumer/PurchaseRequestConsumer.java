package com.demo.kafka.consumer;

import com.demo.kafka.entity.PurchaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseRequestConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RebalanceConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("cachePurchaseRequest")
    private Cache<Integer, Boolean> cache;

    private boolean isExistInCache(int purchaseRequestId) {
        return Optional.ofNullable(cache.getIfPresent(purchaseRequestId)).orElse(false);
    }

    @KafkaListener(topics = "t-purchase-request")
    public void consume(String message) throws JsonProcessingException {
        PurchaseRequest purchaseRequest = objectMapper.readValue(message, PurchaseRequest.class);

        if (isExistInCache(purchaseRequest.getId())) {
            return;
        }

        LOG.info("Processing: {}", purchaseRequest);
        cache.put(purchaseRequest.getId(), true);
    }
}
