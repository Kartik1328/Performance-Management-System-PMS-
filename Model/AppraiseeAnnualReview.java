package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

//We have imported a library for the local date. So that the date can be used as a data type.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appraisee_annual_review")
public class AppraiseeAnnualReview extends BaseModel{
    private String appraiseComment;
    private String managerComment;
    private String appraiseeStrength;
    private String areaOfImprovement;
    private int ratings;
}
