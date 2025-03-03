package com.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingOptionDto {

    private Long trainingMainId;
    private String trainingName;
    private Long trainingOptionId;
}
