package com.pms.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appraisee_goal_setting_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeGoalSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "data")
    @SequenceGenerator(name = "data", allocationSize = 1)
    @Column(nullable = true)

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