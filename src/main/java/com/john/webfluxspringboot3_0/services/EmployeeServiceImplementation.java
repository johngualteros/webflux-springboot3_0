package com.john.webfluxspringboot3_0.services;

import com.john.webfluxspringboot3_0.dao.EmployeeRepository;
import com.john.webfluxspringboot3_0.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor

public class EmployeeServiceImplementation implements  EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public void create(Employee e) {
        employeeRepository.save(e).subscribe();
    }

    public Mono<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Flux<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> update(Employee e) {
        return employeeRepository.save(e);
    }

    public Mono<Void> delete(Integer id) {
        return employeeRepository.deleteById(id);
    }

}
