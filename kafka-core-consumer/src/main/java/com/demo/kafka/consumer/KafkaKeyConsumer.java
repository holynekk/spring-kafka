package com.demo.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.TimeUnit;

//@Service
public class KafkaKeyConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaKeyConsumer.class);

    @KafkaListener(topics = "t-multi-partitions", concurrency = "3")
    public void consumer(ConsumerRecord<String, String> consumerRecord) throws InterruptedException {
        LOG.info("Key: {}, Partition: {}, Message: {}", consumerRecord.key(), consumerRecord.partition(), consumerRecord.value());
        TimeUnit.SECONDS.sleep(1);
    }
}
