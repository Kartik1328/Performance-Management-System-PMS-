package com.pms.co;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
//This I have taken for local date.

//We have imported an library for the local date. So that the date can be used as a data type.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeSelfAssessementDraftCo {

    private Long sad_id;
    
    @PastOrPresent(message="submittedOn is annotated with this validation")
    private LocalDate submittedOn;

    @NotBlank(message = "overallComments cannot be blank")
    private String overallComments;

    @NotNull(message = "overallRating cannot be null")
    private int overallRating;

    @NotBlank(message = "comment cannot be blank")
    private String comment;

    @NotBlank(message = "rating cannot be blank")
    private String rating;

    private Long empId;

    private String selfAssessment;

}


