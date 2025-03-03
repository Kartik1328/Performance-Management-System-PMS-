package com.pms.mapper;
import com.pms.co.AppraiseeDetailsCo;
import com.pms.dto.AppraiseeDetailsDto;
import com.pms.Model.AppraiseeDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AppraiseeDetailsMapper {

    AppraiseeDetailsDto entityToDTO(AppraiseeDetails AppraiseeDetails);

    AppraiseeDetails coToEntity(AppraiseeDetailsCo AppraiseeDetailsCo);

    List<AppraiseeDetailsDto> entityListToDTOList(List<AppraiseeDetails> AppraiseeDetails);

}


