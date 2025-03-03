package com.pms.dto;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeAnnualReviewDto {

    private Long id;
    private String appraiseComment;
    private String managerComment;
    private String appraiseeStrength;
    private String areaOfImprovement;
    private int ratings;

}
