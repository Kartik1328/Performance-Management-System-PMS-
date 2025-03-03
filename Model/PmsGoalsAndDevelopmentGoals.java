package com.pms.Model;

import lombok.Data;

import java.util.List;

@Data
public class PmsGoalsAndDevelopmentGoals {
    private List<AppraiseeDevelopmentGoalSetting> devGoalsList;
    private List<AppraiseeGoalSetting> goalsList;
}
