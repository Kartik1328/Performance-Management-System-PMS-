package com.pms.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pms.Model.PendingAppraisal;

public interface PendingAppraisalRepository extends JpaRepository <PendingAppraisal,Long>{
	//@Query(value="select * from appraisal_cycle_tbl where emp_id=:empId",nativeQuery = true)
//	List<PendingAppraisal> findByEmpIdAndReviewCycle(Long empId,String reviewCycle);

    List<PendingAppraisal> findByReviewCycle(String reviewCycle);
    Boolean existsByReviewCycle(String reviewCycle);
    boolean existsByAppraisalQuarterAndReviewCycle(String appraisalQuarter, String reviewCycle);
}