package com.john.webfluxspringboot3_0.repositories;

import com.john.webfluxspringboot3_0.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {

    void create(Employee e);

    Mono<Employee> findById(Integer id);

    Flux<Employee> findByName(String name);

    Flux<Employee> findAll();

    Mono<Employee> update(Employee e);

    Mono<Void> delete(Integer id);

}
