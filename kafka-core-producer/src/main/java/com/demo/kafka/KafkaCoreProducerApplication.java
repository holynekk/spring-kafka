package com.demo.kafka;

import com.demo.kafka.entity.Image;
import com.demo.kafka.producer.ImageProducer;
import com.demo.kafka.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

//	@Autowired
//	private FoodOrderProducer foodOrderProducer;
//
//	@Autowired
//	private SimpleNumberProducer simpleNumberProducer;

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageProducer imageProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Image img1 = new ImageService().generateImage("jpg");
		Image img2 = new ImageService().generateImage("svg");
		Image img3 = new ImageService().generateImage("png");
		Image img4 = new ImageService().generateImage("gif");
		Image img5= new ImageService().generateImage("bmp");
		Image img6= new ImageService().generateImage("tiff");

		imageProducer.send(img1, 0);
		imageProducer.send(img2, 0);
		imageProducer.send(img3, 0);
		imageProducer.send(img4, 1);
		imageProducer.send(img5, 1);
		imageProducer.send(img6, 1);






//		for(int i = 100; i < 103; i++) {
//			simpleNumberProducer.send(new SimpleNumber(i));
//		}
//
//
//		FoodOrder chickenOrder = new FoodOrder(10, "Chicken");
//		FoodOrder fishOrder = new FoodOrder(3, "Fish");
//		FoodOrder pizzaOrder = new FoodOrder(7, "Pizza");
//
//		foodOrderProducer.send(chickenOrder);
//		foodOrderProducer.send(fishOrder);
//		foodOrderProducer.send(pizzaOrder);


//		PaymentRequest pr_a_1 = new PaymentRequest("a", 551, "USD", "Notes alpha", "aa");
//		PaymentRequest pr_a_2 = new PaymentRequest("a", 551, "USD", "Notes alpha", "bb");
//		PaymentRequest pr_a_3 = new PaymentRequest("a", 551, "USD", "Notes alpha", "cc");
//
//		PaymentRequest pr_b_1 = new PaymentRequest("a", 551, "USD", "Notes beta", "aaa");
//		PaymentRequest pr_b_2 = new PaymentRequest("a", 551, "USD", "Notes beta", "bbb");
//		PaymentRequest pr_b_3 = new PaymentRequest("a", 551, "USD", "Notes beta", "ccc");
//
//		producer.send(pr_a_1);
//		producer.send(pr_a_2);
//		producer.send(pr_a_3);
//
//		producer.send(pr_b_1);
//		producer.send(pr_b_2);
//		producer.send(pr_b_3);
//
//		// Mistake
//		producer.send(pr_a_2);
//		producer.send(pr_b_3);
	}
}
