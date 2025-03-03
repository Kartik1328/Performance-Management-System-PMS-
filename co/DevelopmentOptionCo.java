package com.pms.co;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DevelopmentOptionCo {
    @NotBlank(message = "Deep technical skill cannot be blank")
    private String deepTechnical;

    @NotBlank(message = "Functional skill cannot be blank")
    private String functional;

    @NotBlank(message = "NA (Not Applicable) field cannot be blank")
    private String na;

    @NotBlank(message = "Soft skills cannot be blank")
    private String softSkills;

    @NotBlank(message = "Product testing skill cannot be blank")
    private String productTesting;
}
