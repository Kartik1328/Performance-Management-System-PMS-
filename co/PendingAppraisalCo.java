package com.pms.co;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PendingAppraisalCo {

    @NotBlank(message = "ID cannot be null")
    private Long id;

//    @NotBlank(message = "Employee ID cannot be null")
//    private Long empId;

    @NotBlank(message = "Appraisal quarter cannot be blank")
    private String appraisalQuarter;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @NotBlank(message = "Period from cannot be blank")
    private String periodFrom;

    @NotBlank(message = "Period to cannot be blank")
    private String periodTo;

    @NotBlank(message = "Cycle name cannot be blank")
    private String cycleName;

    @NotBlank(message = "Review cycle cannot be blank")
    private String reviewCycle;
}
