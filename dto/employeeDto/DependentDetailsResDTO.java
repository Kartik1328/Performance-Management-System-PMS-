package com.pms.dto.employeeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DependentDetailsResDTO {
    private long dependentDetailsId;
    private String dependentName;
    private String dependentRelationship;
    private LocalDate dependentDateOfBirth;
}
