package com.pms.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.Model.AppraiseeDetails;

import java.util.Optional;

public interface AppraiseeDetailsRepository extends JpaRepository<AppraiseeDetails,Long> {
    Optional<AppraiseeDetails> findByEmployeeId(Long employeeId);
}
