package com.assignment.Assignment.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetNManager {

    @Valid

    @NotNull(message = "n is mandatory")
    private int n;
    @NotEmpty(message = "id is mandatory")
    private String id;
}
