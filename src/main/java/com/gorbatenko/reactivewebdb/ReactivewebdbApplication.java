package com.gorbatenko.reactivewebdb;

import com.gorbatenko.reactivewebdb.client.CustomerClient;
import com.gorbatenko.reactivewebdb.model.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactivewebdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivewebdbApplication.class, args);

		CustomerClient customerClient = new CustomerClient();

		// починить пост!
		customerClient.post(new Customer(null, "Ivan!!!", "Petrov"));

		//System.out.println(customerClient.get(1L));

		customerClient.getAll().forEach(System.out::println);


		//customerClient.post(new Customer(null, "Petr", "Ivanov"));
	}

}
