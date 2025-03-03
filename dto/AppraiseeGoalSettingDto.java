package com.pms.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeGoalSettingDto {

    private Long goalId;
    private Long empId;
    private String kra;
    @Column(columnDefinition = "TEXT")
    private String goals;
    @Column(columnDefinition = "TEXT")
    private String measurement;
    private String weightage;
    private Long totalWeightage;
    private Long target;
    private String designation;
    private String department;
    private Long subOverallStatusId;
    @Column(columnDefinition = "TEXT")
    private String selfComment;
    private String selfRating;
    @Column(columnDefinition = "TEXT")
    private String managerComment;
    private String managerRating;

}
