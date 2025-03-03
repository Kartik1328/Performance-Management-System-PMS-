package com.pms.dto.employeeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationResDTO {
    private long orgCode;
    private String organizationName;
    private String organizationHierarchy;
}
