package com.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevelopmentOptionDto {

    private Long developmentOptionId;
    private Long trainingOptionId;
    private Long developmentSubId;
    private String developmentOptionName;



}
