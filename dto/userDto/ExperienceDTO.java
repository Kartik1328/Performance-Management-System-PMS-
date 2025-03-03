package com.pms.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDTO {
    private long experienceId;
    private String experience;
    private String companyName;
    private String companyAddress;
    private LocalDate dateOfJoining;
    private LocalDate dateOfReliving;
    private String jobTitle;
    private String certification;
}
