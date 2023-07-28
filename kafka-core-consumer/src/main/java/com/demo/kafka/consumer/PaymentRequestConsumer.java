package com.demo.kafka.consumer;

import com.demo.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;

public class PaymentRequestConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(RebalanceConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("cachePaymentRequest")
    private Cache<PaymentRequestCacheKey, Boolean> cache;

    private boolean isExistInCache(PaymentRequestCacheKey key) {
        return Optional.ofNullable(cache.getIfPresent(key)).orElse(false);
    }

    @KafkaListener(topics = "t-payment-request")
    public void consume(String message) throws JsonProcessingException {
        PaymentRequest paymentRequest = objectMapper.readValue(message, PaymentRequest.class);
        PaymentRequestCacheKey key = new PaymentRequestCacheKey(paymentRequest.getPaymentNumber(), paymentRequest.getAmount(), paymentRequest.getTransactionType());

        if (isExistInCache(key)) {
            return;
        }

        LOG.info("Processing: {}", paymentRequest);
        cache.put(key, true);
    }
}
