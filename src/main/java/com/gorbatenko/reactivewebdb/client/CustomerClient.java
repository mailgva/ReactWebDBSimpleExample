package com.gorbatenko.reactivewebdb.client;

import com.gorbatenko.reactivewebdb.model.Customer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CustomerClient {

    WebClient client = WebClient.create("http://localhost:8080");

    public void post(Customer customer) {
        /*WebClient.RequestHeadersSpec requestSpec1 = WebClient
                .create("http://localhost:8080")
                .method(HttpMethod.POST)
                .uri("/customer")
                .body(BodyInserters.fromPublisher(Mono.just(customer), Customer.class));*/

        client.post().uri("/customer")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .bodyToMono(Customer.class);

        /*client.post()
                .uri("/customer")
                .body(BodyInserters.fromPublisher(Mono.just(customer), Customer.class));*/
    }

    public Customer get(Long id) {
        Mono<Customer> customerMono = client.get()
                .uri("/customer/{id}", id)
                .retrieve()
                .bodyToMono(Customer.class);

        return customerMono.block();

    }

    public List<Customer> getAll() {
        Flux<Customer> customers = client.get()
                .uri("/customer/")
                .retrieve()
                .bodyToFlux(Customer.class);

        return customers.collectList().block();

    }
}