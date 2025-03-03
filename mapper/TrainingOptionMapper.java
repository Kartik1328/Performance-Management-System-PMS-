package com.pms.mapper;

import com.pms.Model.TrainingOption;
import com.pms.co.TrainingOptionCo;
import com.pms.dto.TrainingOptionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface TrainingOptionMapper {
    TrainingOption coToEntity(TrainingOptionCo trainingOption_co);
    TrainingOptionDto entityToDTO(TrainingOption trainingOption);
    List<TrainingOptionDto> entityListToDTOList(List<TrainingOption> trainingOption);
}

