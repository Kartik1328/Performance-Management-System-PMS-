package com.pms.dto.employeeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResDTO {
    private long projectId;
    private String projectName;
    private String projectShortName;
    private String projectCode;
    private String wbsName;
}
