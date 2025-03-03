package com.pms.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeStatusDto {

    private Long employeeStatusId;
    private Long empId;
    private Long userId;
    private Long appraisalQuarterId;
    private String appraisalCycle;
    private String subForApproval;
    private LocalDate submittedOn;
    private String managerApproval;
    private  LocalDate approvedOn;
    private String selfReview;
    private  LocalDate submittedOnn;
    private  String manager;
    private Long mgrId;
    private  LocalDate reviewedOnn;
    private String managerReview;
    private  String currentStatus;
    private String status;
    private String year;
    private String reviewCycle;
    private String mgrRevertComments;
    private String selfOverallComments;
    private int selfOverallRating;
    @Column(columnDefinition = "TEXT")
    private String managerOverallComments;
    private int managerOverallRating;

    public EmployeeStatusDto(Long employeeStatusId, String revertCommentsUpdatedSuccessfully) {
    }
}