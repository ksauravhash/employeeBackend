package com.assignment.Assignment.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Valid

    @Id
    String id;

    String EmployeeName;

    String PhoneNumber;

    String Email;

    @DBRef
    Employee ReportsTo;

    String ProfileImage;
}
