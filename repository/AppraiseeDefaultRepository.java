package com.pms.repository;

import com.pms.Model.AppraiseeDefaultGoals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppraiseeDefaultRepository extends JpaRepository <AppraiseeDefaultGoals,Long> {
    List<AppraiseeDefaultGoals> findByDepartmentAndDesignation(String department, String designation);
}
