package com.assignment.Assignment.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Assignment.DTO.*;
import com.assignment.Assignment.Models.Employee;
import com.assignment.Assignment.Services.EmployeeService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {

        try {
            return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(),
                    HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occured!");
        }

    }

    @PostMapping(path = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addEmployee(@RequestBody @Valid AddEmployee employee) {
        try {
            String ob = employeeService.addEmployee(employee);
            GetEmployeeId g = new GetEmployeeId(ob);
            return new ResponseEntity<GetEmployeeId>(g, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occured!");

        }
    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEmployeeById(@RequestBody @Valid DeleteEmployee de) {
        try {
            employeeService.deleteEmployee(de);
            return ResponseEntity.ok("{\"success\": true}");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occured!");
        }
    }

    @PostMapping(path = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addEmployee(@RequestBody @Valid UpdateEmployee employee) {
        try {
            String ob = employeeService.updateEmployee(employee);
            GetEmployeeId g = new GetEmployeeId(ob);
            return new ResponseEntity<GetEmployeeId>(g, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occured!");

        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}
