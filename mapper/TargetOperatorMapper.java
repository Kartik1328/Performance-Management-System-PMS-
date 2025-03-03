package com.pms.mapper;

import com.pms.Model.TargetOperator;
import com.pms.co.TargetOperatorCo;
import com.pms.dto.TargetOperatorDto;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")

public interface TargetOperatorMapper {
    TargetOperator coToEntity(TargetOperatorCo targetOperator_co);
    TargetOperatorDto entityToDTO(TargetOperator targetOperator);
    List<TargetOperatorDto> entityListToDTOList(List<TargetOperator> targetOperator);
}
