package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

//We have imported an library for the local date. So that the date can be used as a data type.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appraisee_development_goal_setting")

@ToString
public class AppraiseeDevelopmentGoalSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long dg_id;
//  where ID is written that means it is the primary key of the table and that variable is very important
//  here ID is for dg_id(development goals)
//  bigint is used as Long in java


    private Long employeeId;
    private Long trainingOptionId;
    private Long developmentSubId;
    private String selfAssessment;
    private String managerAssessment;
    private String training;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String goal;
    private Long subOverallStatusId;

}