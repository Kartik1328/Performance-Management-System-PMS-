package com.pms.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatutoryDetailsDTO {
    private long statutoryDetailsId;

    private String panNumber;

    private long panCopyDocumentId;

    private String aadhaarNumber;

    private long aadhaarCopyDocumentId;

    private String nameAsPerPanCard;

    private String UAN;

    private String pfNo;

    private String esicNo;

    private String fatherOrHusbandName;

    private String relationshipWithPerson;

    private String earlierMemberOfPF;

    private String internationalWorker;

    private String speciallyAbled;

    private String pFLinkedBankName;

    private String pFLinkedBankAccountNo;

    private String pFLinkedBankIfsc;

    private String lWDOfPreviousCompany;
}
