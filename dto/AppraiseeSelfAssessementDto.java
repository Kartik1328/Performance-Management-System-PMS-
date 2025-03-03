package com.pms.dto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeSelfAssessementDto {

    private Long sa_id;
    private LocalDate submittedOn;
    private String overallComments;
    private int overallRating;
    private String comment;
    private String rating;
    private Long empId;
}


