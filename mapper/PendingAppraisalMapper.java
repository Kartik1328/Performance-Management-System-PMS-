package com.pms.mapper;

import com.pms.Model.PendingAppraisal;
import com.pms.co.PendingAppraisalCo;
import com.pms.dto.PendingAppraisalDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PendingAppraisalMapper {
    PendingAppraisal coToEntity(PendingAppraisalCo pendingAppraisal_co);
    PendingAppraisalDto entityToDTO(PendingAppraisal pendingAppraisal);
    List<PendingAppraisalDto> entityListToDTOList(List<PendingAppraisal> pendingAppraisal);

    List<PendingAppraisal> coListToEntityList(List<PendingAppraisalCo> pendingAppraisalCoList);

    List<PendingAppraisalDto> entityListToDtoList(List<PendingAppraisal> pendingAppraisalList);
}
