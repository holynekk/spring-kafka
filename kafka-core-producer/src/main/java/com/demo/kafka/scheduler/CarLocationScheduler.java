package com.demo.kafka.scheduler;

import com.demo.kafka.entity.CarLocation;
import com.demo.kafka.producer.CarLocationProducer;
import com.demo.kafka.producer.FixedRateProducer2;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarLocationScheduler {
    private static final Logger LOG = LoggerFactory.getLogger(FixedRateProducer2.class);
    private CarLocation car1;
    private CarLocation car2;
    private CarLocation car3;

    @Autowired
    private CarLocationProducer producer;

    public CarLocationScheduler() {
        long now = System.currentTimeMillis();
        car1 = new CarLocation("car-one", now, 0);
        car2 = new CarLocation("car-two", now, 110);
        car3 = new CarLocation("car-three", now, 95);
    }

//    @Scheduled(fixedRate = 1000)
    public void generateCarLocation() throws JsonProcessingException {
        long now = System.currentTimeMillis();

        car1.setTimestamp(now);
        car2.setTimestamp(now);
        car3.setTimestamp(now);

        car1.setDistance(car1.getDistance() + 1);
        car2.setDistance(car2.getDistance() - 1);
        car3.setDistance(car3.getDistance() + 1);

        producer.send(car1);
        producer.send(car2);
        producer.send(car3);

        LOG.info("Sent: {}", car1);
        LOG.info("Sent: {}", car2);
        LOG.info("Sent: {}", car3);
    }
}
