package com.pms.co;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TotalEmployeeStatusCo {

    private Long empId;
    private Long employeeCode;
    private String fullName;
    private String designation;
    private String experience;

    private LocalDate reviewedOnn;
    private  LocalDate submittedOnn;
    private  LocalDate approvedOn;
    private LocalDate submittedOn;

}
