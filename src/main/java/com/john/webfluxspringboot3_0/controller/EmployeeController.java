package com.john.webfluxspringboot3_0.controller;

import com.john.webfluxspringboot3_0.model.Employee;
import com.john.webfluxspringboot3_0.services.EmployeeServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/employees")

public class EmployeeController {

    private final EmployeeServiceImplementation employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee e) {
        employeeService.create(e);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);

        if( e == null ) {
            ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Employee not exists with this id");
            pd.setType(URI.create("http://localhost:8080/employees"));
            pd.setTitle("Employee not found");
            pd.setProperty("hostname", "localhost:8080");

            return ResponseEntity.status(404).body(pd);
        }

        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }

    @GetMapping
    public Flux<Employee> findAll() {
        Flux<Employee> employees = employeeService.findAll();
        return employees;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee e) {
        return employeeService.update(e);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        employeeService.delete(id).subscribe();
    }

}
