package com.pms.mapper;
import com.pms.Model.AppraiseeSelfAssessement;
import com.pms.co.AppraiseeSelfAssessementCo;
import com.pms.dto.AppraiseeSelfAssessementDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AppraiseeSelfAssessementMapper {

	AppraiseeSelfAssessementDto entityToDTO(AppraiseeSelfAssessement AppraiseeSelfAssessement);

	AppraiseeSelfAssessement coToEntity(AppraiseeSelfAssessementCo AppraiseeSelfAssessementCo);

	List<AppraiseeSelfAssessementDto> entityListToDTOList(List<AppraiseeSelfAssessement> AppraiseeSelfAssessement);

}


