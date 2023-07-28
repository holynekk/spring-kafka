package com.demo.kafka;

import com.demo.kafka.entity.PaymentRequest;
import com.demo.kafka.producer.PaymentRequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

	@Autowired
	private PaymentRequestProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PaymentRequest pr_a_1 = new PaymentRequest("a", 551, "USD", "Notes alpha", "aa");
		PaymentRequest pr_a_2 = new PaymentRequest("a", 551, "USD", "Notes alpha", "bb");
		PaymentRequest pr_a_3 = new PaymentRequest("a", 551, "USD", "Notes alpha", "cc");

		PaymentRequest pr_b_1 = new PaymentRequest("a", 551, "USD", "Notes beta", "aaa");
		PaymentRequest pr_b_2 = new PaymentRequest("a", 551, "USD", "Notes beta", "bbb");
		PaymentRequest pr_b_3 = new PaymentRequest("a", 551, "USD", "Notes beta", "ccc");

		producer.send(pr_a_1);
		producer.send(pr_a_2);
		producer.send(pr_a_3);

		producer.send(pr_b_1);
		producer.send(pr_b_2);
		producer.send(pr_b_3);

		// Mistake
		producer.send(pr_a_2);
		producer.send(pr_b_3);
	}
}
