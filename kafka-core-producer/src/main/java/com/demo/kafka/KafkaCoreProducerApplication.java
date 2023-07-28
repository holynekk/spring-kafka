package com.demo.kafka;

import com.demo.kafka.producer.EmployeeJSONProducer2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

	@Autowired
	private EmployeeJSONProducer2 producer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		for(int i = 0; i < 5; i++) {
//			Employee employee = new Employee("employee-" + i, "Employee " + i, LocalDate.now());
//			producer.sendMessage(employee);
//		}
	}
}
