package com.john.webfluxspringboot3_0.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Scope (scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
@Data

public class Employee {

    @Id
    int id;
    String name;
    long salary;

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }

}
