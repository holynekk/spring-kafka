package com.demo.kafka.producer;

import com.demo.kafka.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJSONProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Employee employee) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(employee);
        kafkaTemplate.send("t-employee", json);
    }
}
