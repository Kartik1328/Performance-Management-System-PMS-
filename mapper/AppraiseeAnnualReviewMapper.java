package com.pms.mapper;
import com.pms.co.AppraiseeAnnualReviewCo;
import com.pms.dto.AppraiseeAnnualReviewDto;
import com.pms.Model.AppraiseeAnnualReview;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")

public interface AppraiseeAnnualReviewMapper {

    AppraiseeAnnualReviewDto entityToDTO(AppraiseeAnnualReview AppraiseeAnnualReview);

    AppraiseeAnnualReview coToEntity(AppraiseeAnnualReviewCo AppraiseeAnnualReviewCo);

    List<AppraiseeAnnualReviewDto> entityListToDTOList(List<AppraiseeAnnualReview> AppraiseeAnnualReview);

}


