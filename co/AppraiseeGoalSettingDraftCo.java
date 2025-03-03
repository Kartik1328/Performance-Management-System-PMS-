package com.pms.co;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeGoalSettingDraftCo {
    @NotBlank(message = "Employee ID cannot be null")
    private Long empId;

    @NotBlank(message = "KRA cannot be blank")
    private String kra;

    @NotBlank(message = "Goals cannot be blank")
    @Column(columnDefinition = "TEXT")
    private String goals;

    @NotBlank(message = "Measurement cannot be blank")
    @Column(columnDefinition = "TEXT")
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

    @NotBlank(message = "Sub ID cannot be null")
    private Long subOverallStatusId;

    @NotBlank(message = "selfComment cannot be blank")
    @Column(columnDefinition = "TEXT")
    private String selfComment;

    @NotBlank(message = "selfRating cannot be blank")
    private String selfRating;

    @NotBlank(message = "managerComment cannot be blank")
    @Column(columnDefinition = "TEXT")
    private String managerComment;

    @NotBlank(message = "managerRating cannot be blank")
    private String managerRating;


}
