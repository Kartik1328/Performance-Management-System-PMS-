package com.pms.mapper;

import com.pms.Model.AppraiseeGoalSetting;
import com.pms.dto.AppraiseeGoalSettingDto;
import org.mapstruct.Mapper;
import com.pms.co.AppraiseeGoalSettingCo;


import java.util.List;

@Mapper(componentModel = "spring")
public interface AppraiseeGoalSettingMapper {
    List<AppraiseeGoalSetting> coListToEntityList(List<AppraiseeGoalSettingCo> appraiseeGoalSetting_co);

    AppraiseeGoalSettingDto entityToDTO(AppraiseeGoalSetting appraiseeGoalSetting);

    List<AppraiseeGoalSettingDto> entityListToDTOList(List<AppraiseeGoalSetting> appraiseeGoalSettingList);
}
