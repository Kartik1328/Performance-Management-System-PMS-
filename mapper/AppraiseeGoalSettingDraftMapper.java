package com.pms.mapper;

import com.pms.Model.AppraiseeDevelopmentGoalSettingDraft;
import com.pms.Model.AppraiseeGoalSettingDraft;
import com.pms.co.AppraiseeDevelopmentGoalSettingDraftCo;
import com.pms.dto.AppraiseeGoalSettingDraftDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppraiseeGoalSettingDraftMapper {
    AppraiseeGoalSettingDraftDto coToEntity(AppraiseeDevelopmentGoalSettingDraftCo appraiseeDevelopmentGoalSettingDraft_co);
    AppraiseeGoalSettingDraftDto entityToDTO(AppraiseeDevelopmentGoalSettingDraft appraiseeDevelopmentGoalSettingDraft);
    List<AppraiseeGoalSettingDraftDto> entityListToDTOList(List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDrafts);
}


