package com.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingAppraisalDto {

    private Long pendingAppraisalId;
//    private Long empId;
    private String appraisalQuarter;
    private String status;
    private String periodFrom;
    private String periodTo;
    private String cycleName;
    private String reviewCycle;
}
