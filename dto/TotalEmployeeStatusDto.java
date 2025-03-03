package com.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalEmployeeStatusDto {

    private Long empId;
    private Long employeeCode;
    private String fullName;
    private String designation;
    private String experience;

    private  LocalDate reviewedOnn;
    private  LocalDate submittedOnn;
    private  LocalDate approvedOn;
    private LocalDate submittedOn;

}
