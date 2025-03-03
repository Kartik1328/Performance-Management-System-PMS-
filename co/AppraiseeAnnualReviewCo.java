package com.pms.co;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeAnnualReviewCo {

    private Long id;

    @NotBlank(message="appraiseComment cannot be blank")
    private String appraiseComment;

    @NotBlank(message="managerComment cannot be blank")
    private String managerComment;

    @NotBlank(message="appraiseeStrength cannot be blank")
    private String appraiseeStrength;

    @NotBlank(message="areaOfImprovement cannot be blank")
    private String areaOfImprovement;

    @NotNull(message="ratings cannot be null")
    private int ratings;

}
