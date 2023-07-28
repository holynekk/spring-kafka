package com.demo.kafka.producer;

import com.demo.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void send (PaymentRequest paymentRequest) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(paymentRequest);
        kafkaTemplate.send("t-purchase-request", paymentRequest.getPaymentNumber(), json);
    }
}
