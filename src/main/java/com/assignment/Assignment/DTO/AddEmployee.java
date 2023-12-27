package com.assignment.Assignment.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddEmployee {

    @Valid

    @NotEmpty(message = "Name is required")
    String EmployeeName;

    @NotEmpty(message = "Phone number is required")
    String PhoneNumber;

    @NotEmpty(message = "Email is required")
    String Email;

    String ProfileImage;
}
