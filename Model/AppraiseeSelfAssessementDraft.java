package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Table;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appraisee_self_assessement_draft")

public class AppraiseeSelfAssessementDraft extends BaseModel{

    private Long sad_id;
    private String overallComments;
    private int overallRating;
    private String comment;
    private String rating;
    private String selfAssessment;
    private Long empId;

}


