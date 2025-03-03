package com.pms.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="appraisee_goal_setting_draft_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeGoalSettingDraft {

    private static long idCounter = 0;
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "data")
    @SequenceGenerator(name = "data", allocationSize = 1)
    @Column(nullable = true)
    @Id
    private Long goalDraftId;
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
    public static long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(long idCounter) {
        AppraiseeGoalSettingDraft.idCounter = idCounter;
    }


}