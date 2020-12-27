package com.gorbatenko.reactivewebdb.repository;

import com.gorbatenko.reactivewebdb.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}
