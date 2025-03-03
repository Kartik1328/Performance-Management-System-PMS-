package com.pms.mapper;
import com.pms.co.AppraiseeManagerAssessmentCo;
import com.pms.dto.AppraiseeManagerAssessmentDto;
import com.pms.Model.AppraiseeManagerAssessment;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")

public interface AppraiseeManagerAssessmentMapper {

   AppraiseeManagerAssessmentDto entityToDTO(AppraiseeManagerAssessment AppraiseeManagerAssessment);

   AppraiseeManagerAssessment coToEntity(AppraiseeManagerAssessmentCo AppraiseeManagerAssessmentCo);

   List<AppraiseeManagerAssessmentDto> entityListToDTOList(List<AppraiseeManagerAssessment> AppraiseeManagerAssessment);

}


