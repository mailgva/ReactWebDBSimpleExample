package com.gorbatenko.reactivewebdb.controller;

import com.gorbatenko.reactivewebdb.model.Customer;
import com.gorbatenko.reactivewebdb.repository.CustomerRepository;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customer")
    Mono<Void> create(@RequestBody Publisher<Customer> customerFlux) {
        return customerRepository.saveAll(customerFlux).then();
    }

    @GetMapping("/customer")
    public Flux<Customer> list() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Mono<Customer> findById(@PathVariable Long id) {
        return customerRepository.findById(id);
    }
}
