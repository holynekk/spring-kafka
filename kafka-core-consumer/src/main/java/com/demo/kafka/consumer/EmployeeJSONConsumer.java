package com.demo.kafka.consumer;

import com.demo.kafka.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJSONConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeJSONConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-employee-2")
    public void listen(String message) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(message, Employee.class);
        LOG.info("Employee is: {}", employee);
    }
}
