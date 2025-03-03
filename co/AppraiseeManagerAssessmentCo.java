package com.pms.co;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeManagerAssessmentCo {

    private Long id;

    @PastOrPresent(message="reviewedOn is annotated with this validation")
    private LocalDate reviewedOn;

    @NotBlank(message = "mgrComment cannot be blank")
    private String mgrComment;

    @NotBlank(message = "mgrComment cannot be blank")
    private String overallMgrComments;

    @NotNull(message = "overallMgrRating cannot be null")
    private int overallMgrRating;

    @NotBlank(message = "revertComment cannot be blank")
    private String revertComment;

    @NotBlank(message = "mgrRating cannot be blank")
    private String mgrRating;

    @NotBlank(message = "mgrName cannot be blank")
    private String mgrName;

    private Long mgrId;
//    A FOREIGN KEY IS THERE IN THIS TABLE
}
