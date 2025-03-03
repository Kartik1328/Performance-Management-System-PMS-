package com.pms.repository;
import com.pms.Model.AppraiseeDefaultGoals;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.Model.EmployeeStatus;

import java.util.List;
import java.util.Optional;


public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus,Long> {
	List<EmployeeStatus> findByempId(Long empId);
//	List<EmployeeStatus> findByYearAndReviewCycle(String year,String reviewCycle);
	List<EmployeeStatus> findByReviewCycleAndEmpId(String reviewCycle, Long empId);


	// unique row
	EmployeeStatus findByEmpIdAndAppraisalCycleAndReviewCycle(Long empId,String quarter, String reviewCycle);

	EmployeeStatus findByAppraisalCycleAndReviewCycle(String quarter, String reviewCycle);

//	List<EmployeeStatus> findByCurrentStatus(String currentStatus);
	List<EmployeeStatus> findByCurrentStatusAndManager(String currentStatus,String manager);


	List<EmployeeStatus> findByReviewCycle(String reviewCycle);

	//    repository "GET API" FOR employee status(earlier master table dummy) TABLE
    // 	--new method created for manager screen 2 where the status of all the employee is shown-----------

	List<EmployeeStatus> findByMgrId(String mgrId);

	EmployeeStatus findByEmpIdAndEmployeeStatusId(Long empId, Long employeeStatusId);

	boolean existsByEmpIdAndYearAndReviewCycle(Long empId,String year,String reviewCycle);

	boolean existsByEmpIdAndEmployeeStatusId(Long empId,Long employeeStatusId);



}
