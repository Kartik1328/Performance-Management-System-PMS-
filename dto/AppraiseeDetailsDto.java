package com.pms.dto;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeDetailsDto {

    private Long id;
    private String empName;
    private String designation;
    private String department;
    private String mgrName;
    private String location;
    private String eStatus;
    private String contactNo;
    private String email;
    private Long employeeId;
}


