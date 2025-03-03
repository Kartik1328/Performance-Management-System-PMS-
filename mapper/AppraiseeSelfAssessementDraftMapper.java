package com.pms.mapper;
import com.pms.co.AppraiseeSelfAssessementDraftCo;
import com.pms.dto.AppraiseeSelfAssessementDraftDto;
import com.pms.Model.AppraiseeSelfAssessementDraft;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")

public interface AppraiseeSelfAssessementDraftMapper {

	AppraiseeSelfAssessementDraftDto entityToDTO(AppraiseeSelfAssessementDraft AppraiseeSelfAssessementDraft);

	AppraiseeSelfAssessementDraft coToEntity(AppraiseeSelfAssessementDraftCo AppraiseeSelfAssessmentDraftCo);

	List<AppraiseeSelfAssessementDraftDto> entityListToDTOList(List<AppraiseeSelfAssessementDraft> AppraiseeSelfAssessementDraft);

}


