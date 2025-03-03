package com.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeDevelopmentGoalSettingDraftDto {

    private Long dgDraftId;
    private Long employeeId;
    private Long trainingOptionId;
    private Long developmentSubId;
    private String selfAssessment;
    private String managerAssessment;
    private String training;
    private String description;
    private String goal;
    private Long subOverallStatusId;

}
