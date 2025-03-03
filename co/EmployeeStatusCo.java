package com.pms.co;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeStatusCo {
    @NotBlank(message = "Employee ID cannot be null")
    private Long empId;

    @NotBlank(message = "Appraisal cycle cannot be blank")
    private String appraisalCycle;

    @NotBlank(message = "Submission for approval cannot be blank")
    private String subForApproval;

    @NotBlank(message = "Submission date cannot be null")
    private LocalDate submittedOn;

    @NotBlank(message = "Manager approval status cannot be blank")
    private String managerApproval;

    @NotBlank(message = "Approval date cannot be null")
    private LocalDate approvedOn;

    @NotBlank(message = "Self-review cannot be blank")
    private String selfReview;

    @NotBlank(message = "Self-review submission date cannot be null")
    private LocalDate submittedOnn;

    @NotBlank(message = "Manager cannot be blank")
    private String manager;

    @NotBlank(message = "Self-review reviewed date cannot be null")
    private LocalDate reviewedOnn;

    @NotBlank(message = "Manager review cannot be blank")
    private String managerReview;

    @NotBlank(message = "Current status cannot be blank")
    private String currentStatus;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @NotBlank(message = "Year cannot be blank")
    private String year;

    @NotBlank(message = "Review cycle cannot be blank")
    private String reviewCycle;

    @NotBlank(message = "mgrRevertComments  cannot be blank")
    private String mgrRevertComments;
}
