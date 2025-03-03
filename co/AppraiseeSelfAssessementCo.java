package com.pms.co;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
//This I have taken for local date.


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeSelfAssessementCo {

    private Long sa_id;

    @PastOrPresent(message="LocalDate is annotated with this validation")
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
}


