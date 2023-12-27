package com.assignment.Assignment.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetEmployeesWithPaginationAndSorting {

    private int pageNo;
    @NotNull(message = "sortBy is required")
    @NotEmpty(message = "sortBy is required")
    private String sortBy;
}
