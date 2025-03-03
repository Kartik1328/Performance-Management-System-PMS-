package com.pms.co;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingOptionCo {

    @NotBlank(message = "Training name cannot be blank")
    private String trainingName;
    private Long trainingMainId;
}
