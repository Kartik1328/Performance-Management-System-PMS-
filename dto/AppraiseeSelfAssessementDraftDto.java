package com.pms.dto;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeSelfAssessementDraftDto {

    private Long sad_id;
//    private LocalDate submittedOn;
    private String overallComments;
    private int overallRating;
    private String comment;
    private String rating;
    private String selfAssessment;
    private Long empId;
}


