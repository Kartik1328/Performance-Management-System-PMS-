package com.pms.Model;

import lombok.Data;

import java.util.List;

@Data
public class PmsDraftGoalsAndDraftDevelopmentGoals {
    private List<AppraiseeDevelopmentGoalSettingDraft> devGoalsDraftList;
    private List<AppraiseeGoalSettingDraft> goalsDraftList;
}
