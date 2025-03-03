package com.pms.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String createdBy;
    @Column(updatable = false)
    private LocalDate createdAt;
    private String updatedBy;
    @Column(insertable = false)
    private LocalDate updatedAt;
    //these 4 columns are common in most of the tables that is why it is written in the BASE MODEL.
    private Long subOverallStatusId;
    private String subQuarter;
    private String subYear;
    private String subReviewCycle;
}
