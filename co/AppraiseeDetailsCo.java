package com.pms.co;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeDetailsCo {

    private Long id;

    @NotBlank(message="empName cannot be blank")
    private String empName;

    @NotBlank(message="designation cannot be blank")
    private String designation;

    @NotBlank(message="appraiseComment cannot be blank")
    private String department;

    @NotBlank(message="mgrName cannot be blank")
    private String mgrName;

    @NotBlank(message="location cannot be blank")
    private String location;

    @NotBlank(message="eStatus cannot be blank")
    private String eStatus;

    @NotBlank(message="contactNo cannot be blank")
    private String contactNo;

    @NotBlank(message="email cannot be blank")
    private String email;

    private Long employeeId;
}


