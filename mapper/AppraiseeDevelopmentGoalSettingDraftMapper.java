package com.pms.mapper;

import com.pms.Model.AppraiseeDevelopmentGoalSettingDraft;
import com.pms.co.AppraiseeDevelopmentGoalSettingDraftCo;
import com.pms.dto.AppraiseeDevelopmentGoalSettingDraftDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AppraiseeDevelopmentGoalSettingDraftMapper {
//    List<AppraiseeDevelopmentGoalSettingDraft> coToEntity(List<AppraiseeDevelopmentGoalSettingDraft_co> appraiseeDevelopmentGoalSettingDraft_co);
//    AppraiseeDevelopmentGoalSettingDraft_dto entityToDTO(AppraiseeDevelopmentGoalSettingDraft appraiseeDevelopmentGoalSettingDraft);
//    List<AppraiseeDevelopmentGoalSettingDraft_dto> entityListToDTOList(List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft);
List<AppraiseeDevelopmentGoalSettingDraft> coToEntity(List<AppraiseeDevelopmentGoalSettingDraftCo> appraiseeDevelopmentGoalSettingDraft_co);

    // Mapping Entity to DTO
    AppraiseeDevelopmentGoalSettingDraftDto entityToDTO(AppraiseeDevelopmentGoalSettingDraft appraiseeDevelopmentGoalSettingDraft);

    // Mapping Entity list to DTO list
    List<AppraiseeDevelopmentGoalSettingDraftDto> entityListToDTOList(List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft);
}

