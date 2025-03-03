package com.pms.co;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeDevelopmentGoalSettingDraftCo {
    @NotBlank(message = "Employee ID cannot be null")
    private Long employeeId;

    @NotBlank(message = "Training cannot be blank")
    private String training;

    @NotBlank(message = "Goal cannot be blank")
    private String goal;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Self-assessment cannot be blank")
    private String selfAssessment;

    @NotBlank(message = "Manager assessment cannot be blank")
    private String managerAssessment;

    @NotBlank(message = "sub Id cannot be null")
    private Long subOverallStatusId;

    @NotBlank(message = "Training Option Id cannot be null")
    private Long trainingOptionId;

    @NotBlank(message = "Development Option Id cannot be null")
    private Long developmentSubId;
}