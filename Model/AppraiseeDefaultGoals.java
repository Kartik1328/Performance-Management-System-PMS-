package com.pms.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="appraisee_default_goals")
@Data

public class AppraiseeDefaultGoals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="data",allocationSize=1)

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
