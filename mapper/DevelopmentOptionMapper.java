package com.pms.mapper;

import com.pms.Model.DevelopmentOption;
import com.pms.co.DevelopmentOptionCo;
import com.pms.dto.DevelopmentOptionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DevelopmentOptionMapper {
    DevelopmentOption coToEntity(DevelopmentOptionCo developmentOption_co);
    DevelopmentOptionDto entityToDTO(DevelopmentOption developmentOption);
    List<DevelopmentOptionDto> entityListToDTOList(List<DevelopmentOption> developmentOption);
}

