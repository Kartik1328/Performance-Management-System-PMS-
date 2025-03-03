package com.pms.co;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeDefaultGoalsCo {

    @NotBlank(message = "Employee ID cannot be null")
    private Long empId;

    @NotBlank(message = "KRA cannot be blank")
    private String kra;

    @NotBlank(message = "Goals cannot be blank")
    private String goals;

    @NotBlank(message = "Measurement cannot be blank")
    private String measurement;

    @NotBlank(message = "Weightage cannot be blank")
    private String weightage;

    @NotBlank(message = "Total weightage cannot be null")
    private Long totalWeightage;

    @NotBlank(message = "Target cannot be blank")
    private Long target;

    @NotBlank(message = "Designation cannot be blank")
    private String designation;

    @NotBlank(message = "Department cannot be blank")
    private String department;
}
