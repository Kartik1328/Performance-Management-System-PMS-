package com.pms.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDTO {
    private long skillId;
    private String name;
    private String level;
    private String experience;
    private String rating;
}
