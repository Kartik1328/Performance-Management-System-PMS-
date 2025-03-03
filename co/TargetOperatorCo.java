package com.pms.co;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetOperatorCo {

    @NotBlank(message = "TargetOperator cannot be blank")

    private String targetOperator;
}
