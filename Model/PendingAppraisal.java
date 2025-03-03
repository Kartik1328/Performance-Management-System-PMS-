package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="appraisal_cycle_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PendingAppraisal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pendingAppraisalId;
    //private Long empId;
    private String appraisalQuarter;
    private String status;
    private String periodFrom;
    private String periodTo;
    private String cycleName;
    private String reviewCycle;

}
