package com.pms.co;

import com.pms.Model.AppraiseeDevelopmentGoalSetting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeDevelopmentGoalSettingCo {
    private Long dg_id;

    @NotBlank(message = "Employee ID cannot be null")
    private Long employeeId;

    @NotBlank(message = "Training Option Id cannot be null")
    private Long trainingOptionId;

    @NotBlank(message = "Development Option Id cannot be null")
    private Long developmentSubId;

    @NotBlank(message = "sub Id cannot be null")
    private Long subOverallStatusId;

    @NotBlank(message = "Self-assessment cannot be blank")
    private String selfAssessment;

    @NotBlank(message = "Manager assessment cannot be blank")
    private String managerAssessment;

    @NotBlank(message = "Training cannot be blank")
    private String training;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Goal cannot be blank")
    private String goal;




//    @NotBlank(message = "Sub-quarter cannot be blank")
//    private String subQuarter;
//
//    @NotBlank(message = "Sub-year cannot be blank")
//    private String subYear;
//
//    @NotBlank(message = "Sub-review cycle cannot be blank")
//    private String subReviewCycle;
}



