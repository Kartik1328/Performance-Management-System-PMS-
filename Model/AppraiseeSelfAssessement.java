package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import jakarta.persistence.Table;

//We have imported an library for the local date. So that the date can be used as a data type.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appraisee_self_assessement")
@AttributeOverride(name = "id", column = @Column(name = "sa_id"))
public class AppraiseeSelfAssessement extends BaseModel{
    private LocalDate submittedOn;
    private String overallComments;
    private int overallRating;
    private String comment;
    private String rating;
    private Long empId;
}


