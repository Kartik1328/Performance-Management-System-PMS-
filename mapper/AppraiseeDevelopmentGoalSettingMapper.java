package com.pms.mapper;

import com.pms.Model.AppraiseeDevelopmentGoalSetting;
import com.pms.co.AppraiseeDevelopmentGoalSettingCo;
import com.pms.dto.AppraiseeDevelopmentGoalSettingDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppraiseeDevelopmentGoalSettingMapper {
    AppraiseeDevelopmentGoalSetting coToEntity(AppraiseeDevelopmentGoalSettingCo appraiseeDevelopmentGoalSetting_co);
    AppraiseeDevelopmentGoalSettingDto entityToDTO(AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting);
    List<AppraiseeDevelopmentGoalSettingDto> entityListToDTOList(List<AppraiseeDevelopmentGoalSetting> appraiseeDevelopmentGoalSettingList);

    List<AppraiseeDevelopmentGoalSetting> coListToEntityList(List<AppraiseeDevelopmentGoalSettingCo> appraiseeDevelopmentGoalSettingCoList);



}
