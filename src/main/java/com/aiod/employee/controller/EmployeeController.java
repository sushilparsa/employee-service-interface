package com.aiod.employee.controller;

import com.aiod.employee.repository.EmployeeRepository;
import com.aiod.employee.exception.EmployeeNotFoundException;
import com.aiod.employee.model.Employee;
import com.aiod.employee.payload.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @CrossOrigin
    @GetMapping("/employees")
    public Flux<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/employees")
    public Mono<Employee> createEmployees(@Valid @RequestBody Employee employee) {

        return employeeRepository.save(employee);
    }

    @CrossOrigin
    @GetMapping("/employees/{id}")
    public Mono<ResponseEntity<Employee>> getEmployeeById(@PathVariable(value = "id") String id) {
        return employeeRepository.findById(id).map(savedEmployee -> ResponseEntity.ok(savedEmployee))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PutMapping("/employees/{id}")
    public Mono<ResponseEntity<Employee>> updateEmployee(@PathVariable(value = "empId") String id,
            @Valid @RequestBody Employee employee) {
        return employeeRepository.findById(id).flatMap(existingEmployee -> {
            existingEmployee.setName(employee.getName());
            return employeeRepository.save(existingEmployee);
        }).map(updateEmployee -> new ResponseEntity<>(updateEmployee, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @DeleteMapping("/employees/{id}")
    public Mono<ResponseEntity<Void>> deleteEmployee(@PathVariable(value = "id") String id) {

        return employeeRepository.findById(id)
                .flatMap(existingEmlpoyee -> employeeRepository.delete(existingEmlpoyee)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tweets are Sent to the client as Server Sent Events
    @CrossOrigin
    @GetMapping(value = "/stream/employees", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> streamAllEmployee() {
        return employeeRepository.findAll();
    }

    @CrossOrigin
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("A Employee with the same id already exists"));
    }

    @CrossOrigin
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
