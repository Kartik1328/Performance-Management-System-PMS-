package com.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeDefaultGoalsDto {

    private Long defaultGoalsId;
//    private Long empId;
    private String Kra;
    private String Goals;
    private String Measurement;
    private String Weightage;
    private Long TotalWeightage;
    private Long Target;
    private String designation;
    private String department;
}
