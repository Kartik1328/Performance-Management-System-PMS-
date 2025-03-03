package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import jakarta.persistence.Table;

//We have imported a library for the local date. So that the date can be used as a data type.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appraisee_manager_assessment")

public class AppraiseeManagerAssessment extends BaseModel {
    private LocalDate reviewedOn;
    private String mgrComment;
    private String overallMgrComments;
    private int overallMgrRating;
    private String revertComment;
    private String mgrRating;
    private String mgrName;
    private Long mgrId;
}
