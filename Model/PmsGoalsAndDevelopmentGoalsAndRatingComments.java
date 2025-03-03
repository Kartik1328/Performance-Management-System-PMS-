package com.pms.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pms.dto.OverAllRatingAndComments;
import lombok.Data;

import java.util.List;
@Data
public class PmsGoalsAndDevelopmentGoalsAndRatingComments {
    private List<AppraiseeDevelopmentGoalSetting> devGoalsList;
    private List<AppraiseeGoalSetting> goalsList;

    //----------------------------------------------------------------
   // @JsonProperty("overAllRatingAndComments")
    @JsonProperty("overAllGoalsAndRatingComments")
    private OverAllRatingAndComments overAllGoalsAndRatingComments;
//    private List<OverAllRatingAndComments> overAllGoalsAndRatingComments;
//    private OverAllRatingAndComments overAllGoalsAndRatingComments;
}
