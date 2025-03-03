package com.pms.dto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeManagerAssessmentDto {

    private Long id;
    private LocalDate reviewedOn;
    private String mgrComment;
    private String overallMgrComments;
    private int overallMgrRating;
    private String revertComment;
    private String mgrRating;
    private String mgrName;
    private Long mgrId;
//    A FOREIGN KEY IS THERE IN THIS TABLE
}
