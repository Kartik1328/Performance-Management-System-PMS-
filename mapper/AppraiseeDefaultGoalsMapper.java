package com.pms.mapper;

import com.pms.Model.AppraiseeDefaultGoals;
import com.pms.co.AppraiseeDefaultGoalsCo;
import com.pms.dto.AppraiseeDefaultGoalsDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AppraiseeDefaultGoalsMapper {

    AppraiseeDefaultGoals coToEntity(AppraiseeDefaultGoalsCo appraiseeDefaultGoals_co);
    AppraiseeDefaultGoalsDto entityToDTO(AppraiseeDefaultGoals appraiseeDefaultGoals);

    List<AppraiseeDefaultGoalsDto> entityListToDTOList(List<AppraiseeDefaultGoals> appraiseeDefaultGoals);
}

