package com.assignment.Assignment.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateEmployee {

    String id;

    String EmployeeName;

    String PhoneNumber;

    String Email;

    String ProfileImage;
}
